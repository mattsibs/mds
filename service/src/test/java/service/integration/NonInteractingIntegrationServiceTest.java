package service.integration;

import com.mds.data.math.DeltaTime;
import com.mds.data.math.Iteration;
import com.mds.data.math.TimeUnit;
import com.mds.data.math.Vector;
import com.mds.data.particle.Particle;
import com.mds.service.integration.NonInteractingIntegrationService;
import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NonInteractingIntegrationServiceTest {

    @Test
    public void iterate_particleReleasedFromStationary_doesNotChangeCurrentIteration() {
        Iteration startingIteration = new Iteration(
                new Vector(0.0, 0.0, 0.0),
                new Vector(0.0, 0.0, 0.0),
                new Vector(0.0, 0.0, 0.0)
        );
        Particle particle = new Particle(startingIteration, null);

        DeltaTime deltaTime = new DeltaTime(1, TimeUnit.MILLI);
        NonInteractingIntegrationService nonInteractingIntegrationService = new NonInteractingIntegrationService(deltaTime);

        Particle iterate = nonInteractingIntegrationService.iterate(particle);
        assertThat(iterate.getCurrentIteration()).isEqualTo(startingIteration);
    }

    @Test
    public void iterate_particleReleasedFromStationary_increasesVelocityDownwards() {
        Iteration startingIteration = new Iteration(
                new Vector(0.0, 0.0, 0.0),
                new Vector(0.0, 0.0, 0.0),
                new Vector(0.0, -9.8, 0.0)
        );
        Particle particle = new Particle(startingIteration, null);

        DeltaTime deltaTime = new DeltaTime(1, TimeUnit.MILLI);
        NonInteractingIntegrationService nonInteractingIntegrationService = new NonInteractingIntegrationService(deltaTime);

        Particle iterate = nonInteractingIntegrationService.iterate(particle);

        assertThat(iterate.getNextIteration().getVelocity().getY())
                .isCloseTo(-0.0098, Offset.offset(0.0001));
    }

}