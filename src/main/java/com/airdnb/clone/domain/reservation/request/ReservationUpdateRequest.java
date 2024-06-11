package com.airdnb.clone.domain.reservation.request;

public record ReservationUpdateRequest(
        Long memberId,
        Long reservationId,
        Integer guestCount
) {
}
