package com.airdnb.clone.domain.filter.service;

import com.airdnb.clone.domain.filter.controller.request.AvailableStayRequest;
import com.airdnb.clone.domain.reservation.ReservationRepository;
import com.airdnb.clone.domain.stay.controller.dto.response.StayDetailResponse;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.repository.StayRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FilterService {

    private final StayRepository stayRepository;
    private final ReservationRepository reservationRepository;

    public List<StayDetailResponse> getAvailableStays(AvailableStayRequest request) {
        // 검색조건인 체크인, 체크아웃 시간을 기준으로 이용 불가능한 숙소의 ID 찾기
        List<Long> unAvailableStayIds = reservationRepository.findUnavailableStayIdsByCheckInOut(
                request.getCheckInDate(), request.getCheckOutDate());

        // 이용불가능한 숙소ID, 1박 최소 및 최대 금액, 최대 게스트 수에 따라 필터링
        List<Stay> availableStays = stayRepository.findAvailableStays(
                unAvailableStayIds, request.getMinPrice(), request.getMaxPrice(), request.getGuestCount());

        return availableStays.stream()
                .map(StayDetailResponse::of)
                .toList();
    }
}
