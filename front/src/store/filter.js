import { get, writable } from "svelte/store";
import { router } from "tinro";
import { format } from "date-fns";
import { stays } from "./stays.js";
import { data } from "autoprefixer";

function setFilters() {
  let initValues = {
    checkIn: format(new Date(), "yyyy-MM-dd"),
    checkOut: format(
      new Date(new Date().setDate(new Date().getDate() + 7)),
      "yyyy-MM-dd"
    ),
    checkInDate: format(new Date(), "M월 d일"),
    checkOutDate: format(
      new Date(new Date().setDate(new Date().getDate() + 7)),
      "M월 d일"
    ),
    rateRange: [10000, 1000000],
    traveler: 1,
    latitude: 37.536094,
    longitude: 126.967522,
    cursorId: 0,
  };

  // 외부에서 사용할 수 있도록 설정
  const { subscribe, update, set } = writable({ ...initValues });

  // 검색 버튼 누를 때
  const searchWithCondition = async () => {
    const checkIn = get(filters).checkIn.trim();
    const checkOut = get(filters).checkOut.trim();
    const latitude = get(filters).latitude;
    const longitude = get(filters).longitude;
    const minPrice = get(filters).rateRange[0];
    const maxPrice = get(filters).rateRange[1];
    const traveler = get(filters).traveler;
    const cursorId = get(filters).cursorId;

    const today = new Date();
    const startDay = format(today, "yyyy-MM-dd");
    let endDay = "";
    if (checkOut === "" && checkIn === "") {
      endDay = format(
        new Date(today.setDate(today.getDate() + 7)),
        "yyyy-MM-dd"
      );
    } else if (checkOut === "" && checkIn !== "") {
      endDay = format(
        new Date(checkIn).setDate(new Date(checkIn).getDate() + 7),
        "yyyy-MM-dd"
      );
    }

    const checkInCond = checkIn.length !== 0 ? checkIn : startDay;
    const checkOutCond = checkOut.length !== 0 ? checkOut : endDay;

    console.log(
      "search condition ===> ",
      checkInCond,
      checkOutCond,
      minPrice,
      maxPrice,
      traveler,
      latitude,
      longitude
    );

    const searchCond = `cursorId=${cursorId}&checkInDate=${checkInCond}&checkOutDate=${checkOutCond}&minPrice=${minPrice}&maxPrice=${maxPrice}&guestCount=${traveler}&latitude=${latitude}&longitude=${longitude}`;

    update((data) => {
      data.checkInDate = format(new Date(checkInCond), "M월 d일");
      data.checkOutDate = format(new Date(checkOutCond), "M월 d일");
      return data;
    });

    await stays.fetchStaysBySearchCond(searchCond);

    router.goto(`/stays`);
  };

  /* 체크인, 체크아웃 업데이트 */
  const updateCheckInOut = async (checkIn, checkOut) => {
    update((data) => {
      data.checkIn = checkIn;
      data.checkOut = checkOut;
      console.log("updated check In & Out ===> ", checkIn, checkOut);
      return data;
    });
  };

  /* 요금 범위 업데이트 */
  const updateRateRange = async (range) => {
    update((data) => {
      data.rateRange[0] = Math.min(...range);
      data.rateRange[1] = Math.max(...range);
      console.log(
        "updated minPrice & maxPrice ===> ",
        data.rateRange[0],
        data.rateRange[1]
      );
      return data;
    });
  };

  /* 여행자 수 업데이트 */
  const updateTraveler = async (traveler) => {
    update((data) => {
      data.traveler = traveler;
      console.log("updated traveler ===> ", traveler);
      return data;
    });
  };

  /* 사용자 동의 시 현재 위치의 위도와 경도 상태를 업데이트 */
  const updateCurrentGeo = async (latitude, longitude) => {
    update((data) => {
      data.latitude = latitude;
      data.longitude = longitude;
      console.log("updated coords ===> ", latitude, longitude);
      return data;
    });
  };

  /* 체크 인 아웃 초기화 */
  const resetCheckInOut = async () => {
    update((data) => {
      data.checkIn = "";
      data.checkOut = "";
      return data;
    });
  };

  /* 요금 범위 초기화 */
  const resetRateRange = async () => {
    update((data) => {
      data.rateRange[0] = 10000;
      data.rateRange[1] = 1000000;
      return data;
    });
  };

  /* 여행자 수 초기화 */
  const resetTraveler = async () => {
    update((data) => {
      data.traveler = 1;
      return data;
    });
  };

  /* cursorId 증가 */
  const increaseCursorId = (cursorId) => {
    update((data) => {
      data.cursorId = cursorId;
      return data;
    });
  };

  return {
    subscribe,

    searchWithCondition, // 검색 버튼

    updateCheckInOut, // 체크인 체크아웃 업데이트
    updateRateRange, // 요금 범위 업데이트
    updateTraveler, // 여행자 수 업데이트
    updateCurrentGeo, // 현재 위치 (위도 경도) 업데이트

    resetCheckInOut, // 체크인 아웃 초기화
    resetRateRange, // 요금 범위 초기화
    resetTraveler, // 여행자 수 초기화

    increaseCursorId,
  };
}

export const filters = setFilters();
