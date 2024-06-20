package com.airdnb.clone.data;

public class DummyLongitudeGenerator {
    private static final double MIN_LONGITUDE = 126.903500;
    private static final double MAX_LONGITUDE = 128.826900;

    public static double generate() {
        double randomLongitude = RandomNumberGenerator.generateDouble(MIN_LONGITUDE, MAX_LONGITUDE);
        String format = String.format("%.6f", randomLongitude);
        return Double.parseDouble(format);
    }
}
