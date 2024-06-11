package com.airdnb.clone.domain.reservation.request;

public record ReservationDeleteRequest(
        Long memberId, // 권한이 있는 사용자인지 확인할 때 필요
        Long reservationId
) {
}
