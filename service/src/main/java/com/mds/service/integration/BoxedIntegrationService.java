package com.mds.service.integration;

import com.mds.data.math.Iteration;
import com.mds.data.math.Plane;
import com.mds.data.math.Vector;
import com.mds.data.particle.Particle;
import com.mds.service.integration.box.Box;

import java.util.List;

public class BoxedIntegrationService implements IntegrationService {

    private final IntegrationService delegate;
    private final Box box;

    public BoxedIntegrationService(final IntegrationService delegate, final Box box) {
        this.delegate = delegate;
        this.box = box;
    }

    @Override
    public Particle iterate(final Particle particle) {
        Particle iteratedParticle = delegate.iterate(particle);
        Vector particlePosition = iteratedParticle.getNextIteration().getPosition();

        List<Plane> planesTraversed = box.planesTraversed(particlePosition);

        if (planesTraversed.isEmpty()) {
            return iteratedParticle;
        }

        Plane plane = planesTraversed.get(0);
        Iteration nextIteration = particle.getNextIteration();

        Vector reflectedPosition = nextIteration
                .getPosition()
                .reflection(plane);

        Vector reflectedVelocity = nextIteration
                .getPosition()
                .reflection(plane);

        particle.setNextIteration(
                new Iteration(
                        nextIteration.getPosition(),
                        reflectedVelocity,
                        nextIteration.getAcceleration()
                ));

        return particle;
    }
}
