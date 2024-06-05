package com.airdnb.clone.domain.stay.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Amenity {
    private Long id;
    private Long stayId;
    private String mainCategory;
    private String name;
    private String description;

    @Builder
    public Amenity(Long stayId, String mainCategory, String name, String description) {
        this.stayId = stayId;
        this.mainCategory = mainCategory;
        this.name = name;
        this.description = description;
    }
}
