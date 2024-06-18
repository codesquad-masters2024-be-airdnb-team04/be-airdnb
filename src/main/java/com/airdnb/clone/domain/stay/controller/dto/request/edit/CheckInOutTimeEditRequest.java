package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CheckInOutTimeEditRequest {

    @NotNull
    private final LocalTime checkInTime;

    @NotNull
    private final LocalTime checkOutTime;
}
