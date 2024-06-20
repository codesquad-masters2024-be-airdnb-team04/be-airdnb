package com.airdnb.clone.data;

public class DummyDescriptionGenerator {
    private static final String name = "설명";
    private static int count = 0;

    public static String generate() {
        return name + ++count;
    }
}
