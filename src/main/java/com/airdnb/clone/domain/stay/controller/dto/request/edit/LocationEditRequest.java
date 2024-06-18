package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LocationEditRequest {

    @NotBlank
    private final String location;
}
