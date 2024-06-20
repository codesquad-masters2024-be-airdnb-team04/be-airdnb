package com.airdnb.clone.data;

public class DummyLatitudeGenerator {
    private static final double MIN_LATITUDE = 35.187030;
    private static final double MAX_LATITUDE = 37.73094;

    public static double generate() {
        double randomLatitude = RandomNumberGenerator.generateDouble(MIN_LATITUDE, MAX_LATITUDE);
        String format = String.format("%.6f", randomLatitude);
        return Double.parseDouble(format);
    }
}
