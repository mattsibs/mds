package com.particle.data.math;

import com.mds.data.math.TimeUnit;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeUnitTest {

    @Test
    public void getMultiplier_MILLI_returnsOneMillisecond() {
        assertThat(TimeUnit.MILLI.getMultiplier())
                .isEqualTo(0.001);
    }

    @Test
    public void getMultiplier_MICRO_returnsOneMicrosecond() {
        assertThat(TimeUnit.MICRO.getMultiplier())
                .isEqualTo(0.000001);
    }

    @Test
    public void getMultiplier_NANO_returnsOneNanosecond() {
        assertThat(TimeUnit.NANO.getMultiplier())
                .isEqualTo(0.000000001);
    }

    @Test
    public void getMultiplier_PICO_returnsOneNanosecond() {
        assertThat(TimeUnit.PICO.getMultiplier())
                .isEqualTo(0.000000000001);
    }

}