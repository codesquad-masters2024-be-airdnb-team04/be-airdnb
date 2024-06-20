package com.airdnb.clone.data;

import java.util.Random;

public class DummyFacilityGenerator {

    private static final int MIN_BED_ROOM = 1;
    private static final int MAX_BED_ROOM = 5;
    private static final int MIN_BATH_ROOM = 1;
    private static final int MAX_BATH_ROOM = 5;
    private static final int MIN_BED = 1;
    private static final int MAX_BED = 20;

    public static int generateBedRoomCount() {
        return generate(MIN_BED_ROOM, MAX_BED_ROOM);
    }

    public static int generateBathRoomCount() {
        return generate(MIN_BATH_ROOM, MAX_BATH_ROOM);
    }

    public static int generateBedCount() {
        return generate(MIN_BED, MAX_BED);
    }

    private static int generate(int min, int max) {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }
}
