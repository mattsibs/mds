package com.mds.service.integration;

import com.mds.data.math.Vector;
import com.mds.data.particle.Particle;
import com.mds.service.integration.box.Box;

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

        if (box.inBox(particlePosition)) {
            return iteratedParticle;
        }


        return null;
    }
}
