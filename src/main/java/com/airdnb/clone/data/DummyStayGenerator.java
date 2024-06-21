package com.airdnb.clone.data;

import static com.airdnb.clone.util.PointUtil.createPoint;

import com.airdnb.clone.domain.stay.entity.RoomInformation;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.entity.StayFee;

public class DummyStayGenerator {

    public static Stay generate() {
        return Stay.builder()
                .alias(DummyAliasGenerator.generate())
                .checkInTime(DummyCheckInGenerator.generate())
                .checkOutTime(DummyCheckoutGenerator.generate())
                .description(DummyDescriptionGenerator.generate())
                .fee(StayFee.builder()
                        .perNight(DummyStayFeeGenerator.generatePerNightRate())
                        .cleaningFee(DummyStayFeeGenerator.generateCleaningFee())
                        .build())
                .roomInfo(RoomInformation.builder()
                        .bedCount(DummyFacilityGenerator.generateBedCount())
                        .bathCount(DummyFacilityGenerator.generateBathRoomCount())
                        .bedroomCount(DummyFacilityGenerator.generateBedRoomCount())
                        .guestCount(DummyTravelerGenerator.generate())
                        .build())
                .type(DummyTypeGenerator.generate())
                .point(createPoint(DummyLatitudeGenerator.generate(), DummyLongitudeGenerator.generate()))
                .build();
    }

    public static Stay generateBusan() {
        return Stay.builder()
                .alias(DummyAliasGenerator.generate())
                .checkInTime(DummyCheckInGenerator.generate())
                .checkOutTime(DummyCheckoutGenerator.generate())
                .description(DummyDescriptionGenerator.generate())
                .fee(StayFee.builder()
                        .perNight(DummyStayFeeGenerator.generatePerNightRate())
                        .cleaningFee(DummyStayFeeGenerator.generateCleaningFee())
                        .build())
                .roomInfo(RoomInformation.builder()
                        .bedCount(DummyFacilityGenerator.generateBedCount())
                        .bathCount(DummyFacilityGenerator.generateBathRoomCount())
                        .bedroomCount(DummyFacilityGenerator.generateBedRoomCount())
                        .guestCount(DummyTravelerGenerator.generate())
                        .build())
                .type(DummyTypeGenerator.generate())
                .point(createPoint(DummyLatitudeGenerator.generateBusan(), DummyLongitudeGenerator.generateBusan()))
                .build();
    }

    public static Stay generateJeju() {
        return Stay.builder()
                .alias(DummyAliasGenerator.generate())
                .checkInTime(DummyCheckInGenerator.generate())
                .checkOutTime(DummyCheckoutGenerator.generate())
                .description(DummyDescriptionGenerator.generate())
                .fee(StayFee.builder()
                        .perNight(DummyStayFeeGenerator.generatePerNightRate())
                        .cleaningFee(DummyStayFeeGenerator.generateCleaningFee())
                        .build())
                .roomInfo(RoomInformation.builder()
                        .bedCount(DummyFacilityGenerator.generateBedCount())
                        .bathCount(DummyFacilityGenerator.generateBathRoomCount())
                        .bedroomCount(DummyFacilityGenerator.generateBedRoomCount())
                        .guestCount(DummyTravelerGenerator.generate())
                        .build())
                .type(DummyTypeGenerator.generate())
                .point(createPoint(DummyLatitudeGenerator.generateJeju(), DummyLongitudeGenerator.generateJeju()))
                .build();
    }

    public static Stay generateGangwon() {
        return Stay.builder()
                .alias(DummyAliasGenerator.generate())
                .checkInTime(DummyCheckInGenerator.generate())
                .checkOutTime(DummyCheckoutGenerator.generate())
                .description(DummyDescriptionGenerator.generate())
                .fee(StayFee.builder()
                        .perNight(DummyStayFeeGenerator.generatePerNightRate())
                        .cleaningFee(DummyStayFeeGenerator.generateCleaningFee())
                        .build())
                .roomInfo(RoomInformation.builder()
                        .bedCount(DummyFacilityGenerator.generateBedCount())
                        .bathCount(DummyFacilityGenerator.generateBathRoomCount())
                        .bedroomCount(DummyFacilityGenerator.generateBedRoomCount())
                        .guestCount(DummyTravelerGenerator.generate())
                        .build())
                .type(DummyTypeGenerator.generate())
                .point(createPoint(DummyLatitudeGenerator.generateGangwon(), DummyLongitudeGenerator.generateGangwon()))
                .build();
    }
}
