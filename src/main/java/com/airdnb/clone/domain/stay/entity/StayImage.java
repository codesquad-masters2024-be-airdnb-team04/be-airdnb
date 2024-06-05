package com.airdnb.clone.domain.stay.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StayImage {

    private String url;
    private String place;

    public StayImage(String url, String place) {
        this.url = url;
        this.place = place;
    }
}
