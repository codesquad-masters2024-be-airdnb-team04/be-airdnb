package com.airdnb.clone.domain.booking.controller.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookingSaveRequest {

    @NotNull
    private final Long memberId;

    @NotNull
    private final Long stayId;

    @Future
    private final LocalDateTime checkIn;

    @Future
    private final LocalDateTime checkOut;

    @Min(value = 1)
    private final Integer guestCount;
}
