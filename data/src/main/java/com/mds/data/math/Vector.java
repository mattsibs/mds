package com.mds.data.math;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Vector {

    private final double x;
    private final double y;
    private final double z;

    public Vector reflection(final Plane plane) {
        double pointPlaneDistance = plane.pointPlaneDistance(this);
        Vector reflectingVector = plane.getPlaneVector()
                .unitVector()
                .scale(-2.0 * pointPlaneDistance);

        return this.add(reflectingVector);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector scale(final double coefficient) {
        return new Vector(
                coefficient * x,
                coefficient * y,
                coefficient * z
        );
    }

    public double dotProduct(final Vector otherVector) {
        return x * otherVector.getX()
                + y * otherVector.getY()
                + z * otherVector.getZ();
    }

    public Vector add(final Vector otherVector) {
        return new Vector(
                x + otherVector.getX(),
                y + otherVector.getY(),
                z + otherVector.getZ()
        );
    }

    public Vector minus(final Vector otherVector) {
        return new Vector(
                x - otherVector.getX(),
                y - otherVector.getY(),
                z - otherVector.getZ()
        );
    }

    public Vector unitVector() {
        return scale(1.0 / magnitude());
    }
}
