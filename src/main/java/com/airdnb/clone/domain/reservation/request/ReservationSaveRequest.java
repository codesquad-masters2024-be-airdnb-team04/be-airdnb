package com.airdnb.clone.domain.reservation.request;

import com.airdnb.clone.domain.common.Guest;
import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.reservation.entity.Reservation;
import com.airdnb.clone.domain.stay.entity.Stay;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReservationSaveRequest(

        @NotNull
        Long memberId,

        @NotNull
        Long stayId,

        @Future
        LocalDateTime checkIn,

        @Future
        LocalDateTime checkOut,

        @Min(value = 0)
        Long totalRate,

        @Min(value = 1)
        Integer guestCount
) {
    public Reservation toEntity(Member member, Stay stay) {
        return Reservation.builder()
                .member(member)
                .stay(stay)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .totalRate(totalRate)
                .guest(Guest.builder().guestCount(guestCount).build())
                .build();
    }
}
