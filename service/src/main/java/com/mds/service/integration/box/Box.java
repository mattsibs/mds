package com.mds.service.integration.box;

import com.mds.data.math.Plane;
import com.mds.data.math.Vector;

import java.util.List;

public class Box {

    private final List<Plane> planes;

    public Box(final List<Plane> planes) {
        this.planes = planes;
    }

    public boolean inBox(final Vector vector) {
        return planes.stream()
                .allMatch(plane -> insideOfPlane(vector, plane));
    }

    private Boolean insideOfPlane(final Vector vector, final Plane plane) {
        Vector planeVector = plane.getPlaneVector();
        Vector normalVector = planeVector.unitVector();

        double dotProduct = vector.dotProduct(normalVector);
        Vector projectedVector = normalVector.scale(dotProduct);

        if (projectedVector.magnitude() > Math.abs(plane.getDistanceFromOrigin())) {
            return false;
        }

        return true;
    }

}
