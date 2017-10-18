package com.mds.data.math;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class VectorTest {

    @Test
    public void scale_Vector_returnsScaledComponents() throws Exception {
        Vector vector = new Vector(1.0, 2.0, 3.0);

        assertThat(vector.scale(2.0))
                .isEqualTo(new Vector(2.0, 4.0, 6.0));
    }

    @Test
    public void magnitude_Vector_returnsSqrtOfSquaredComponents() throws Exception {
        Vector vector = new Vector(0.0, 3.0, 4.0);

        assertThat(vector.magnitude())
                .isEqualTo(5.0);
    }

    @Test
    public void dotProduct_otherVector_returnsProductsOfCoefficients() throws Exception {
        Vector vector = new Vector(1.0, 2.0, 3.0);
        Vector otherVector = new Vector(4.0, 5.0, 6.0);

        assertThat(vector.dotProduct(otherVector))
                .isEqualTo(32.0);
    }

    @Test
    public void add_otherVector_returnsNewVectorWithSummedComponents() {
        Vector vector = new Vector(1.0, 2.0, 3.0);
        Vector otherVector = new Vector(4.0, 5.0, 6.0);

        assertThat(vector.add(otherVector))
                .isEqualTo(new Vector(5.0, 7.0, 9.0));
    }

    @Test
    public void add_otherVector_returnsNewVectorWithSubtractedComponents() {
        Vector vector = new Vector(4.0, 5.0, 6.0);
        Vector otherVector = new Vector(1.0, 2.0, 3.0);

        assertThat(vector.minus(otherVector))
                .isEqualTo(new Vector(3.0, 3.0, 3.0));
    }

    @Test
    public void reflection_withXYPlaneVectorZDirection_reversesZDirectionVector() throws Exception {
        Vector vector = new Vector(0.0, 0.0, -5.0);

        Plane xyPlaneNormalVector = new Plane(
                new Vector(0.0, 0.0, -1.0),
                0.0
        );

        assertThat(vector.reflection(xyPlaneNormalVector))
                .isEqualTo(new Vector(0.0, 0.0 ,5.0));
    }

    @Test
    public void unitVector_xAxis_returnsSameVector() {
        Vector xAxis = new Vector(1.0, 0.0, 0.0);
        assertThat(xAxis.unitVector())
                .isEqualTo(xAxis);
    }

    @Test
    public void unitVector_combined_returnsSameVector() {
        Vector triplet = new Vector(4.0, 3.0, 0.0);

        assertThat(triplet.unitVector())
                .isEqualTo(triplet.scale(1 / 5.0));
    }
}