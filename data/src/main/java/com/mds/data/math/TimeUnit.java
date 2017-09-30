package com.mds.data.math;

public enum TimeUnit {
    MILLI(-3),
    MICRO(-6),
    NANO(-9),
    PICO(-12);

    private final int power;

    TimeUnit(final int power) {
        this.power = power;
    }

    public double getMultiplier() {
        return Math.pow(10, power);
    }
}
