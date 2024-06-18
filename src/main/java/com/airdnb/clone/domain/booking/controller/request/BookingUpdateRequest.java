package com.airdnb.clone.domain.booking.controller.request;

import com.airdnb.clone.domain.common.Guest;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookingUpdateRequest {

    @Min(value = 1)
    private final Integer guestCount;

    public Guest toGuest() {
        return Guest.builder()
                .guestCount(guestCount)
                .build();
    }
}
