package com.airdnb.clone.domain.stay.controller.dto.request.edit;

import com.airdnb.clone.domain.stay.entity.RoomInformation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoomInfoEditRequest {

    @NotNull
    @Min(value = 1)
    private Integer guestCount;

    @NotNull
    @Min(value = 0)
    private Integer bedroomCount;

    @NotNull
    @Min(value = 1)
    private Integer bedCount;

    @NotNull
    @Min(value = 0)
    private Integer bathCount;

    public RoomInformation toRoomInformation() {
        return RoomInformation.builder()
                .guestCount(guestCount)
                .bedroomCount(bedroomCount)
                .bedCount(bedCount)
                .bathCount(bathCount)
                .build();
    }
}
