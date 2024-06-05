package com.airdnb.clone.domain.stay.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Stay {

    private Long id;
    private Long hostId;
    private String alias;
    private String location;
    private Integer perNight;
    private Integer cleaningFee;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String roomType;
    private StayStatus status;
    private String description;
    private List<Amenity> amenities;
    private List<StayImage> stayImages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private Integer guestCount = 1;

    @Builder.Default
    private Integer bedroomCount = 0;

    @Builder.Default
    private Integer bedCount = 1;

    @Builder.Default
    private Integer bathCount = 0;

    public void changeStayStatus(StayStatus status) {
        this.status = status;
    }

    public enum StayStatus {
        OPEN, REPAIR, CLOSE
    }
}