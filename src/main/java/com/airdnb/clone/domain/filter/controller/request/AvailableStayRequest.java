package com.airdnb.clone.domain.filter.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AvailableStayRequest {

    @NotNull
    private final LocalDate startDate;

    @NotNull
    private final LocalDate endDate;

    @NotNull
    @Min(value = 0)
    private final Integer minPrice;

    @NotNull
    @Min(value = 0)
    private final Integer maxPrice;

    @NotNull
    @Min(value = 0)
    private final Integer guestCount;
}
