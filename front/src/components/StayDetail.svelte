<script>
    import { createEventDispatcher } from 'svelte';
    import {stays} from "../store/stays.js";
    import {filters} from "../store/filter.js";
    import {onMount} from "svelte";
    import {format} from "date-fns";

    const dispatch = createEventDispatcher();

    let stay = null;
    let checkInRate = null;
    let discount = null;
    let cleaningFee = null;
    let commission = null;
    let tax = null;

    $: checkInRate
    $: discount
    $: cleaningFee
    $: commission
    $: tax

    const formattingCurrency = (value) => {
        return value.toLocaleString('ko-KR')
    }

    const getDayDiff = (start, end) => {
      start = new Date(start)
      end = new Date(end)
      return (end - start) / (1000 * 3600 * 24);
    }

    const getTotalRate = (start, end, perNight, cleaningFee) => {
      return getDayDiff(start, end) * perNight + cleaningFee
    }

    const offBookingMode = () => {
      stays.closeBookingPopup();
      dispatch('modalStatus', true)
    }

    onMount(() => {
      const stayId = $stays.bookingPopup;
      stay = $stays.stayList.find(stay => stay.id === stayId);
      checkInRate = stay.fee.perNight * getDayDiff($filters.checkIn, $filters.checkOut)
      discount = stay.fee.perNight * getDayDiff($filters.checkIn, $filters.checkOut) * 0.04
      cleaningFee = stay.fee.cleaningFee
      commission = stay.fee.perNight * getDayDiff($filters.checkIn, $filters.checkOut) * 0.05
      tax = stay.fee.perNight * getDayDiff($filters.checkIn, $filters.checkOut) * 0.005
    })
</script>

{#if stay !== null}
    <div class="animate-fade animate-duration-300 z-[99] border-2 rounded-xl px-6 py-7 fixed top-[50%] left-[50%] transform -translate-x-1/2 -translate-y-1/2 w-[400px] flex flex-col space-y-4 bg-white whitespace-nowrap">
        <div class="flex gap-1 justify-start items-center">
            <span class="text-xl text-gray-800 font-bold">₩ {formattingCurrency(stay.fee.perNight)}</span>
            <div class="text-gray-500">/</div>
            <div class="text-gray-500">박</div>
        </div>

        <div class="border border-gray-400/90 rounded-xl">
            <div class="flex gap-4 px-4">
                <div class="flex-1 flex flex-col gap-1 pt-2 pb-1 border-r border-gray-400/90">
                    <span class="text-[13px] font-[600]">체크인</span>
                    <span class="text-[15px] text-gray-700/80">{format(new Date($filters.checkIn), "yyyy. M. d")}</span>
                </div>
                <div class="flex-1 flex flex-col gap-1 pt-2 pb-1">
                    <span class="text-[13px] font-[600]">체크아웃</span>
                    <span class="text-[15px] text-gray-700/80">{format(new Date($filters.checkOut), "yyyy. M. d")}</span>
                </div>
            </div>
            <div class="border-t gap-4 px-4 py-2 border-gray-400/90">
                <div class="flex-1 flex flex-col gap-1 pt-0.5">
                    <span class="text-[13px] font-[600]">인원</span>
                    <span class="text-[15px] text-gray-700/80">게스트 {$filters.traveler}명</span>
                </div>
            </div>
        </div>

        <button id="reservation" type="button" class="py-4 rounded-xl bg-[#333333] drop-shadow-md hover:bg-black active:transition-all active:scale-[0.99] active:duration-0"
        on:click={offBookingMode}>
            <label for="reservation" class="text-white font-bold text-[1rem] pointer-events-none">예약하기</label>
        </button>


        <div class="flex justify-center">
                <span class="text-[14px] text-gray-500">
                    예약 확정 전에는 요금이 청구되지 않습니다.
                </span>
        </div>

        <div class="pt-1 flex flex-col space-y-2">
            <div class="flex justify-between text-gray-700">
                    <span class="underline">
                        ₩{formattingCurrency(stay.fee.perNight)} x {getDayDiff($filters.checkIn, $filters.checkOut)}박
                    </span>
                <span>
                        ₩{formattingCurrency(checkInRate)}
                    </span>
            </div>
            <div class="flex justify-between text-gray-700">
                    <span class="underline">
                        4% 주 단위 요금 할인
                    </span>
                <span class="text-green-700">
                        -₩{formattingCurrency(discount)}
                    </span>
            </div>
            <div class="flex justify-between text-gray-700">
                    <span class="underline">
                        청소비
                    </span>
                <span>
                        ₩{formattingCurrency(cleaningFee)}
                    </span>
            </div>
            <div class="flex justify-between text-gray-700">
                    <span class="underline">
                        서비스 수수료
                    </span>
                <span>
                        ₩{formattingCurrency(commission)}
                    </span>
            </div>
            <div class="flex justify-between text-gray-700">
                    <span class="underline">
                        숙박세와 수수료
                    </span>
                <span>
                        ₩{tax}
                    </span>
            </div>
        </div>

        <hr class="border-t border-t-gray-300">

        <div class="flex justify-between text-gray-700 font-bold">
                    <span class="underline">
                        총 합계
                    </span>
            <span>
                        ₩{formattingCurrency(checkInRate - discount + cleaningFee + commission + tax)}
                    </span>
        </div>
    </div>
{/if}