package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import com.airdnb.clone.domain.stay.entity.StayFee;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeeEditRequest {

    @NotNull
    private final StayFee fee;
}
