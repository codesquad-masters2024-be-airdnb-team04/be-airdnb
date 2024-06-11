package com.airdnb.clone.domain.reservation.dto;

import com.airdnb.clone.domain.common.Guest;
import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.reservation.entity.Reservation;
import com.airdnb.clone.domain.stay.entity.Stay;
import java.time.LocalDateTime;

public record ReservationSaveRequest(
        Long memberId,
        Long stayId,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        Long totalRate,
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
