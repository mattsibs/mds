package com.mds.service.integration.box;

import com.mds.data.math.Plane;
import com.mds.data.math.Vector;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BoxTest {

    private static final Vector ZERO_VECTOR = new Vector(0.0, 0.0, 0.0);

    private final List<Plane> planes = Arrays.asList(
            new Plane(new Vector(1.0, 0.0, 0.0), -1.0),
            new Plane(new Vector(-1.0, 0.0, 0.0), 1.0),
            new Plane(new Vector(0.0, 1.0, 0.0), 1.0),
            new Plane(new Vector(0.0, -1.0, 0.0), 1.0),
            new Plane(new Vector(0.0, 0.0, 1.0), 1.0),
            new Plane(new Vector(0.0, 0.0, -1.0), 1.0)
    );

    @Test
    public void inBox_VectorIsInsidePositive_returnsTrue() {
        Box box = new Box(planes);
        Vector vector = new Vector(0.5, 0.5, 0.5);

        assertThat(box.inBox(vector)).isTrue();
    }

    @Test
    public void inBox_VectorIsInsideInNegativeArea_returnsTrue() {
        Box box = new Box(planes);
        Vector vector = new Vector(0.5, -0.5, -0.5);

        assertThat(box.inBox(vector)).isTrue();
    }

    @Test
    public void inBox_VectorIsInsideOnlyJust_returnsTrue() {
        Box box = new Box(planes);
        Vector vector = new Vector(1.0, 1.0, 0.9);

        assertThat(box.inBox(vector)).isTrue();
    }

    @Test
    public void inBox_VectorIsOutsideOnlyJust_returnsTrue() {
        Box box = new Box(planes);
        Vector vector = new Vector(1.0, 1.0, 1.1);

        assertThat(box.inBox(vector)).isFalse();
    }

    @Test
    public void inBoxWithNoneZeroVector_VectorIsOutside_returnsTrue() {
        Vector centreVector = new Vector(0.5, 0.0, 0.5);
        Box box = new Box(planes);

        Vector vector = new Vector(1.6, -0.5, -0.5);

        assertThat(box.inBox(vector)).isFalse();
    }
}