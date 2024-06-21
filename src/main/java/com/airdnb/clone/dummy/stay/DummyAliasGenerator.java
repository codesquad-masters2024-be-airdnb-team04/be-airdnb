package com.airdnb.clone.dummy.stay;

public class DummyAliasGenerator {
    private static final String name = "숙소";
    private static int count = 0;

    public static String generate() {
        return name + ++count;
    }
}
