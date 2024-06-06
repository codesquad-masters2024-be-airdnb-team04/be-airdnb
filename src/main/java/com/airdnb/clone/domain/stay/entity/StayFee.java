package com.airdnb.clone.domain.stay.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StayFee {
    private Integer perNight;
    private Integer cleaningFee;

    public StayFee(Integer perNight, Integer cleaningFee) {
        this.perNight = perNight;
        this.cleaningFee = cleaningFee;
    }
}
