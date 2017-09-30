package com.mds.data.math;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
public class Iteration {
    @Field("position")
    private final Vector position;
    @Field("velocity")
    private final Vector velocity;
    @Field("acceleration")
    private final Vector acceleration;
}
