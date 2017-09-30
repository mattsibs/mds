package com.mds.service.integration;

import com.mds.data.math.DeltaTime;
import com.mds.data.math.Iteration;
import com.mds.data.math.Vector;
import com.mds.data.particle.Particle;

public class NonInteractingIntegrationService implements IntegrationService {

    private final DeltaTime deltaTime;

    public NonInteractingIntegrationService(final DeltaTime deltaTime) {
        this.deltaTime = deltaTime;
    }

    @Override
    public Particle iterate(final Particle particle) {
        Vector position = particle.getCurrentIteration().getPosition();
        Vector velocity = particle.getCurrentIteration().getVelocity();
        Vector acceleration = particle.getCurrentIteration().getAcceleration();

        Vector nextPosition = new Vector(
                incrementPosition(position.getX(), velocity.getX(), acceleration.getX()),
                incrementPosition(position.getY(), velocity.getY(), acceleration.getY()),
                incrementPosition(position.getZ(), velocity.getZ(), acceleration.getZ())
        );

        Vector nextVelocity = new Vector(
                incrementVelocity(velocity.getX(), acceleration.getX()),
                incrementVelocity(velocity.getY(), acceleration.getY()),
                incrementVelocity(velocity.getZ(), acceleration.getZ())
        );

        particle.setNextIteration(
                new Iteration(nextPosition, nextVelocity, acceleration)
        );

        return particle;
    }

    private double incrementPosition(final double position, final double velocity, final double acceleration) {
        return position + velocity * deltaTime.getTimeInSeconds() + 0.5 * acceleration * Math.pow(deltaTime.getTimeInSeconds(), 2);
    }

    private double incrementVelocity(final double velocity, final double acceleration) {
        return velocity + acceleration * deltaTime.getTimeInSeconds();
    }
}
