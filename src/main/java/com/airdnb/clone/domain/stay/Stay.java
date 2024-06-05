package com.airdnb.clone.domain.stay;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private AvailableStatus status;
    private String description;
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

    public void changeAvailableStatus(AvailableStatus status) {
        this.status = status;
    }
}