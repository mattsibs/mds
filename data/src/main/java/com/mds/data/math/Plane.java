package com.mds.data.math;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Plane {
    private final Vector planeVector;
    private final double distanceFromOrigin;

    public double pointPlaneDistance(final Vector vector) {
        Vector normalVector = planeVector.unitVector();
        return vector.dotProduct(normalVector);
    }
}
