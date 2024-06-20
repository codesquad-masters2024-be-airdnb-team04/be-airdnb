package com.airdnb.clone.data;

import com.airdnb.clone.domain.stay.entity.AvailableAmenity;
import com.airdnb.clone.domain.stay.entity.RoomInformation;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.entity.StayFee;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class DummyStayGenerator {

    public static Stay generate() {
        return Stay.builder()
                .alias(DummyAliasGenerator.generate())
                .host(DummyMemberGenerator.generate())
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

    private static Point createPoint(double latitude, double longitude) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}
