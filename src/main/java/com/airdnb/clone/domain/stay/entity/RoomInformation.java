package com.airdnb.clone.domain.stay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Embeddable
@EqualsAndHashCode
public class RoomInformation {

    @Builder.Default
    @Column(name = "GUEST_COUNT")
    private Integer guestCount = 1;

    @Builder.Default
    @Column(name = "BEDROOM_COUNT")
    private Integer bedroomCount = 0;

    @Builder.Default
    @Column(name = "BED_COUNT")
    private Integer bedCount = 1;

    @Builder.Default
    @Column(name = "BATH_COUNT")
    private Integer bathCount = 0;

    public void validate(Integer guestCount) {
        if (this.guestCount < guestCount) {
            throw new IllegalArgumentException("최대 허용 인원수 초과, 집이 작아요");
        }
    }
}
