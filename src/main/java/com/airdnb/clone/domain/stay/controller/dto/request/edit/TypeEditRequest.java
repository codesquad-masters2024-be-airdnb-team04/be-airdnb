package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import com.airdnb.clone.domain.stay.entity.Stay.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TypeEditRequest {

    @NotNull
    private final Type type;
}
