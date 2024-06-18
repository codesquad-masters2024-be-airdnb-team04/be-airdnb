package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AmenitiesEditRequest {

    @NotEmpty
    private final List<Long> amenityIds;
}
