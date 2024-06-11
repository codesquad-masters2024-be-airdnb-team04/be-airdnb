package com.airdnb.clone.domain.reservation.dto;

import com.airdnb.clone.domain.reservation.entity.Reservation;
import java.time.LocalDateTime;

public record ReservationResponse(
        Long Id,
        Long memberId,
        Long stayId,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        Long totalRate,
        Integer guestCount
) {
    public static ReservationResponse toDto(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getMember().getId(),
                reservation.getStay().getId(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getTotalRate(),
                reservation.getGuest().getGuestCount()
        );
    }
}
