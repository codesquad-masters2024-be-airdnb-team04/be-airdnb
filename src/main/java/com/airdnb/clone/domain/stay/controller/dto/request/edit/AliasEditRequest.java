package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AliasEditRequest {

    @NotBlank
    private final String alias;
}
