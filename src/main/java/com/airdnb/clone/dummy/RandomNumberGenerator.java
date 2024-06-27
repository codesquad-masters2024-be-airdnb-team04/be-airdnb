package com.airdnb.clone.dummy;

import java.util.Random;

public interface RandomNumberGenerator {
    Random random = new Random();

    static Long generateLong(long min, long max) {
        return random.nextLong(max - min) + min;
    }

    static Long generateLongByInterval(long min, long max, int interval) {
        return (random.nextLong(max - min) / interval) * interval;
    }

    static Integer generateInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    static Integer generateIntByInterval(int min, int max, int interval) {
        return (random.nextInt(max - min) / interval) * interval;
    }

    static Double generateDouble(double min, double max) {
        return random.nextDouble(min, max);
    }
}
