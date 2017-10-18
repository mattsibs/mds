package com.mds.service.integration.box;

import com.mds.data.math.Plane;
import com.mds.data.math.Vector;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Box {

    private final List<Plane> planes;

    public Box(final List<Plane> planes) {
        this.planes = planes;
    }

    public List<Plane> planesTraversed(final Vector vector) {
        return planes.stream()
                .map(plane -> findPlaneTraversed(vector, plane))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Optional<Plane> findPlaneTraversed(final Vector vector, final Plane plane) {
        Vector planeVector = plane.getPlaneVector();
        Vector normalVector = planeVector.unitVector();

        double dotProduct = vector.dotProduct(normalVector);
        Vector projectedVector = normalVector.scale(dotProduct);

        if (projectedVector.magnitude() > Math.abs(plane.getDistanceFromOrigin())) {
            return Optional.of(plane);
        }

        return Optional.empty();
    }

}
