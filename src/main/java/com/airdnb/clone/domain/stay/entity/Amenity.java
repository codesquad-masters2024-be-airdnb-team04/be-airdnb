package com.airdnb.clone.domain.stay.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Amenity {

    private String mainCategory;
    private String name;
    private String description;

    public Amenity(String mainCategory, String name, String description) {
        this.mainCategory = mainCategory;
        this.name = name;
        this.description = description;
    }
}
