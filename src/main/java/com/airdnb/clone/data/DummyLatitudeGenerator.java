package com.airdnb.clone.data;

import java.util.Random;

public class DummyLatitudeGenerator {
    private static final double MIN_LATITUDE = 35.187030;
    private static final double MAX_LATITUDE = 37.73094;

    public static double generate() {
        return new Random().nextDouble(MIN_LATITUDE, MAX_LATITUDE);
    }
}
