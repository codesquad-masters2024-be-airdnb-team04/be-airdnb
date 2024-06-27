<script>
    import {filters} from "../store/filter.js";
    import {stays} from "../store/stays.js";

    export let stay

    const getDayDiff = (start, end) => {
      start = new Date(start)
      end = new Date(end)
      return (end - start) / (1000 * 3600 * 24);
    }

    const getTotalRate = (start, end, perNight, cleaningFee) => {
      return getDayDiff(start, end) * perNight + cleaningFee
    }

    const onBookingMode = (id) => {
      stays.openBookingPopup(id);
    }
</script>

<div class="border-b pb-4 pr-4">
    <!--                예시 1-->
    <button id={stay.id} class="relative flex gap-4" on:click={() => {onBookingMode(stay.id);}}>
        <div class="w-[330px] min-w-[280px] h-[220px] min-h-[200px]">
            <img class="rounded-3xl shadow-lg hover:shadow-xl hover:scale-[0.99]" src="/assets/seoul.jpg" alt="이미지1">
        </div>
        <div class="flex flex-col items-start gap-1 py-1">
            <div class="text-gray-500/80 text-[15px]">
                서초구의 {stay.stayType} 전체
            </div>
            <div class="text-gray-800 text-[1rem] truncate">
                {stay.description}
            </div>
            <div class="flex flex-wrap pr-[4rem] text-gray-500/80 text-[14px]">
                <span>최대 인원 {stay.roomInfo.guestCount}명</span>
                <span> ∙ </span>
                <span>{stay.stayType}</span>
                <span> ∙ </span>
                <span>침실 {stay.roomInfo.bedroomCount}개</span>
                <span> ∙ </span>
                <span>침대 {stay.roomInfo.bedCount}개</span>
                <span> ∙ </span>
                <span>욕실 {stay.roomInfo.bathCount}개</span>
                <span> ∙ </span>
                <span>주방</span>
                <span> ∙ </span>
                <span>무선 인터넷</span>
                <span> ∙ </span>
                <span>에어컨</span>
                <span> ∙ </span>
                <span>헤어드라이어</span>
            </div>
            <div class="absolute flex flex-col bottom-0 right-0 space-y-1">
                <div class="flex gap-1 justify-between">
                    <span class="font-bold">₩{stay.fee.perNight.toLocaleString('ko-KR')}</span>
                    <div class="text-gray-800/80">/</div>
                    <div class="text-gray-800/80">박</div>
                </div>
                <div class="text-[14px] text-gray-500/80 underline hover:text-gray-700">
                    <span>총액 ₩ {getTotalRate($filters.checkIn, $filters.checkOut, stay.fee.perNight, stay.fee.cleaningFee).toLocaleString('ko-KR')}</span>
                </div>
            </div>
        </div>
    </button>
</div>
