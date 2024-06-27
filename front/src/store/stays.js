import { get, writable } from "svelte/store";
import { router } from "tinro";
import { format } from "date-fns";
import { getApi } from "./api.js";
import { URL_PREFIX } from "./constants.js";
import { filters } from "./filter.js";

function setStays() {
  let initValues = {
    stayList: [],
    hasNext: false,
    bookingPopup: "",

    openIssues: 0,
    closedIssues: 0,
    issueList: [],
    editTitlePopup: "",
    editContentPopup: "",
    editCommentPopup: "",
    editModeTitle: "", // 게시글 중 수정모드로 전환 시 게시글의 식별자 저장하는 필드
    editModeContent: "", // 게시글 중 수정모드로 전환 시 게시글의 식별자 저장하는 필드
    editModeComment: "",
  };

  const { subscribe, update, set } = writable({ ...initValues });

  const fetchStaysBySearchCond = async (queryString = "") => {
    loadingStay.turnOnLoading();
    try {
      const options = {
        path: `${URL_PREFIX}/stays?${queryString}`,
      };

      const response = await getApi(options);
      if (response.status !== 200) {
        return console.log("no fetch response ===> ", response);
      }

      const newData = {
        stayList: response.data.content,
        hasNext: response.data.last === false,
      };

      update((data) => {
        data.stayList = [...data.stayList, ...newData.stayList];
        data.hasNext = newData.hasNext;
        console.log("fetch stays ===> ", data.stayList);
        return data;
      });

      loadingStay.turnOffLoading();
    } catch (error) {
      console.log("fetch error stays ===> ", error);
      loadingStay.turnOffLoading();
      throw error;
    }
  };

  const openBookingPopup = (id) => {
    update((data) => {
      data.bookingPopup = id;
      return data;
    });
  };

  const closeBookingPopup = () => {
    update((data) => {
      data.bookingPopup = "";
      return data;
    });
  };

  const resetStays = () => {
    set({ ...initValues });
    staysPageLock.set(false);
  };

  return {
    subscribe,

    resetStays,

    fetchStaysBySearchCond,

    openBookingPopup,
    closeBookingPopup,
  };
}

function setCurrentStaysPage() {
  const { subscribe, update, set } = writable(1);

  const resetPage = () => set(1);
  const increPage = (cursorId) => {
    update((data) => (data = cursorId));
    filters.increaseCursorId(cursorId);
    stays.fetchStaysBySearchCond(); // 페이지 증가할 때 호출
  };

  return {
    subscribe,
    resetPage,
    increPage,
  };
}

function setLoadingStay() {
  const { subscribe, set } = writable(false);

  const turnOnLoading = () => {
    set(true);
    staysPageLock.set(true);
  };
  const turnOffLoading = () => {
    set(false);
    staysPageLock.set(false);
  };

  return {
    subscribe,
    turnOnLoading,
    turnOffLoading,
  };
}

export const stays = setStays();
export const staysPageLock = writable(false);
export const currentStaysPage = setCurrentStaysPage();
export const loadingStay = setLoadingStay();
