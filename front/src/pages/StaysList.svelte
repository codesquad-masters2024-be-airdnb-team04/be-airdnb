<script>
  import Header from "../components/Header.svelte";
  import MyNaverMap from "../components/MyNaverMap.svelte";
  import Footer from "../components/Footer.svelte";
  import StayDetail from "../components/StayDetail.svelte";
  import {filters} from "../store/filter.js";
  import {currentStaysPage, loadingStay, stays, staysPageLock} from "../store/stays.js";
  import {get} from "svelte/store";
  import {onDestroy, onMount} from "svelte";
  import StayItem from "./StayItem.svelte";
  import Modal from "../components/Modal.svelte";
  import StayLoading from "../components/StayLoading.svelte";

  let stayList = [];
  let modalStatus = false;

  /* 스크롤 정보 */
  let element
  let component = document.querySelector('#item');
  $: { // 반응형 기호 사용 => 스크롤할 때, 브라우저 크기가 변할 때마다 돔의 높이 정보, 스크롤 위치가 변하기 때문
    if (component) {
      element = component
      element.addEventListener('scroll', onScroll)
      element.addEventListener('resize', onScroll)
    }
  }

  const onScroll = (e) => {

    const scrollHeight = e.target.scrollHeight // 브라우저 스크롤 높이
    const clientHeight = e.target.clientHeight // 화면 높이
    const scrollTop = e.target.scrollTop // 현재 스크롤 위치
    const realHeight = scrollHeight - clientHeight // 실제 높이
    const triggerHeight = realHeight * 0.7

    const triggerComputed = () => {
      return scrollTop > triggerHeight
    }

    // 현재 페이지가 전체페이지보다 작거나 같으면 true 리턴
    const countCheck = () => {
      return $stays.hasNext;
    }

    // countCheck를 이용해 현재페이지가 페이지 마지막일 경우 staysPageLock을 true로 해서 이를 통해 더이상 페이지가 증가하지 않음
    if (countCheck) {
      staysPageLock.set(true)
    }

    const scrollTrigger = () => {
      return triggerComputed() && countCheck() && $staysPageLock
    }

    if (scrollTrigger()) {
      const lastButtonChild = document.querySelector('#item button:last-child');
      console.log('gogogogogo', lastButtonChild.id)
      if (lastButtonChild) {
        currentStaysPage.increPage(lastButtonChild.id);
      }
    }
  }

  const offBookingMode = () => {
    stays.closeBookingPopup();
  }

  const handleModal = (status) => {
    modalStatus = status
  }

  onMount(() => {
    if (get(stays).stayList.length === 0) {
      console.log('no stay list. start fetch no search condition')
      filters.searchWithCondition();
    }
    stayList = get(stays).stayList;
    console.log('stayList:', stayList)

    // 스크롤 이벤트 핸들러를 추가합니다.
    component = document.querySelector('#item');
    if (component) {
      component.addEventListener('scroll', onScroll);
    }
  })

  // 컴포넌트가 언마운트될 때 이벤트 핸들러를 제거합니다.
  onDestroy(() => {
    if (component) {
      component.removeEventListener('scroll', onScroll);
    }
  });
</script>

<Header scrollMode={false}/>

<div class="flex justify-between items-start pt-4 mt-[80px] pl-3 scroll-auto">
    <!--  리스트  -->
    <div class="flex-1 pl-4">
        <div class="flex flex-col pt-2 space-y-3">
            <!--  300개 이상의 숙소 5월 12일 5월 18일 ₩100,000~₩1,000,000 개스트 3명  -->
            <div class="flex gap-2 text-[14px] text-gray-800">
                <span>300개 이상의 숙소</span>
                <span>·</span>
                <span>{get(filters).checkInDate} ~ {get(filters).checkOutDate}</span>
                <span>·</span>
                <span>₩{get(filters).rateRange[0].toLocaleString('ko-KR')}</span>
                <span>~</span>
                <span>₩{get(filters).rateRange[1].toLocaleString('ko-KR')}</span>
                <span>·</span>
                <span>게스트 {get(filters).traveler}명</span>
            </div>
            <div>
                <div class="font-bold text-black text-3xl">
                    지도에서 선택한 지역의 숙소
                </div>
            </div>

            <!--  숙소 리스트 -->
            <div id="item" on:scroll={onScroll} class="flex flex-col gap-2 max-h-[1000px] items-start justify-start pt-2 space-y-3 overflow-y-scroll">

                {#if stayList.length !== 0}
                    {#each stayList as stay}
                        <StayItem stay={stay}/>
                    {/each}
                {/if}

                {#if $loadingStay}
                    <StayLoading/>
                {/if}

            </div>
        </div>
    </div>

    {#if $stays.bookingPopup !== ''}
        <div class="overlay-dark" on:click={() => offBookingMode()}></div>
        <StayDetail on:modalStatus={e => handleModal(e.detail)}/>
    {/if}

    {#if modalStatus === true}
        <Modal on:modalStatus={e => handleModal(e.detail)}/>
    {/if}

    <!--  지도  -->
    <div class="flex-1 h-[1080px]">
        <MyNaverMap stayList={stayList}/>
    </div>
</div>

<div class="-mt-[6rem]">
    <Footer/>
</div>
