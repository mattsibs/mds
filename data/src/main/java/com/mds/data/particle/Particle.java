package com.mds.data.particle;

import com.mds.data.math.Iteration;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Particle {

    @Id
    private String id;
    @Field("currentIteration")
    private Iteration currentIteration;
    @Field("nextIteration")
    private Iteration nextIteration;

    public Particle(final Iteration currentIteration, final Iteration nextIteration) {
        this.currentIteration = currentIteration;
        this.nextIteration = nextIteration;
    }

    public Particle updateIteration() {
        setCurrentIteration(getNextIteration());
        setNextIteration(null);
        return this;
    }
}