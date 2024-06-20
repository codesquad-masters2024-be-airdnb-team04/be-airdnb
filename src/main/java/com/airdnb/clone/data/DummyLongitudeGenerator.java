package com.airdnb.clone.data;

import java.util.Random;

public class DummyLongitudeGenerator {
    private static final double MIN_LONGITUDE = 126.903500;
    private static final double MAX_LONGITUDE = 128.826900;

    public static double generate() {
        return new Random().nextDouble(MIN_LONGITUDE, MAX_LONGITUDE);
    }
}
