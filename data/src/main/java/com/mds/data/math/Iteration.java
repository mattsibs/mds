package com.mds.data.math;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
@AccessType(AccessType.Type.PROPERTY)
public class Iteration {
    @Field("position")
    private Vector position;
    @Field("velocity")
    private Vector velocity;

    @Field("acceleration")
    private Vector acceleration;

    public Iteration(final Vector position, final Vector velocity, final Vector acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
}
