package com.airdnb.clone.domain.booking.response;

import com.airdnb.clone.domain.booking.entity.Reservation;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingResponse {
    private final Long reservationId;
    private final Long memberId;
    private final Long stayId;
    private final LocalDateTime checkIn;
    private final LocalDateTime checkOut;
    private final Long totalRate;
    private final Integer guestCount;

    public static BookingResponse of(Reservation reservation) {
        return new BookingResponse(
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
