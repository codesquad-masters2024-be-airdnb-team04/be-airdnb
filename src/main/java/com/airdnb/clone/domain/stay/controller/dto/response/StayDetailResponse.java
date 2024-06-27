package com.airdnb.clone.domain.stay.controller.dto.response;

import com.airdnb.clone.domain.stay.controller.dto.response.edit.RoomInfoResponse;
import com.airdnb.clone.domain.stay.entity.Amenity;
import com.airdnb.clone.domain.stay.entity.AvailableAmenity;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.entity.Stay.Status;
import com.airdnb.clone.domain.stay.entity.StayFee;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class StayDetailResponse {

    private final Long id;

    @JsonProperty(value = "stayAlias")
    private final String alias;

    private final double latitude;
    private final double longitude;
    private final LocalTime checkInTime;
    private final LocalTime checkOutTime;
    private final String description;
    private final StayFee fee;

    @JsonProperty(value = "stayType")
    private final String type;

    @JsonProperty(value = "stayStatus")
    private final Status status;

    private final RoomInfoResponse roomInfo;

    public static StayDetailResponse of(Stay stay) {
        return new StayDetailResponse(
                stay.getId(),
                stay.getAlias(),
                stay.getPoint().getY(),
                stay.getPoint().getX(),
                stay.getCheckInTime(),
                stay.getCheckOutTime(),
                stay.getDescription(),
                stay.getFee(),
                stay.getType().getName(),
                stay.getStatus(),
                RoomInfoResponse.of(stay.getRoomInfo())
        );
    }

    private static List<Long> extractAmenityId(List<AvailableAmenity> amenities) {
        return amenities.stream()
                .map(AvailableAmenity::getAmenity)
                .map(Amenity::getId)
                .toList();
    }
}
