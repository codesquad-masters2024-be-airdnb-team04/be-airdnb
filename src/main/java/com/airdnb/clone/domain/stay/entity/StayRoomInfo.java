package com.airdnb.clone.domain.stay.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StayRoomInfo {
    private Integer guestCount = 1;
    private Integer bedroomCount = 0;
    private Integer bedCount = 1;
    private Integer bathCount = 0;

    public StayRoomInfo(Integer guestCount, Integer bedroomCount, Integer bedCount, Integer bathCount) {
        this.guestCount = guestCount;
        this.bedroomCount = bedroomCount;
        this.bedCount = bedCount;
        this.bathCount = bathCount;
    }
}
