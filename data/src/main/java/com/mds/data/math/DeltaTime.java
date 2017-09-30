package com.mds.data.math;

import lombok.Data;

@Data
public class DeltaTime {

    private final int number;
    private final TimeUnit timeUnit;

    public double getTimeInSeconds() {
        return number * timeUnit.getMultiplier();
    }

}
