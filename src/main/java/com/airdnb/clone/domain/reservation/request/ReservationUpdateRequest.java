package com.airdnb.clone.domain.reservation.request;

import jakarta.validation.constraints.Min;

public record ReservationUpdateRequest(
        @Min(value = 1)
        Integer guestCount
) {
}
