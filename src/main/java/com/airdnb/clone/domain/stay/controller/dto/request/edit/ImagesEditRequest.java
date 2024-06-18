package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import com.airdnb.clone.domain.stay.entity.StayImage;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImagesEditRequest {

    @NotEmpty
    private final List<StayImage> images;
}
