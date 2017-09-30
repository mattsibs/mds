package service.loader;


import com.mds.data.math.Iteration;
import com.mds.data.math.Vector;
import com.mds.data.particle.Particle;
import com.mds.service.loader.ParticleLoader;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticleLoaderTest {

    private static final String FREE_FALL_PATH = "src/test/resources/loader/freeFall.csv";
    private static final String LATTICE_PATH = "src/test/resources/loader/lattice.csv";

    @Test
    public void load_freeFallParticle_returnsOneParticleWithFreeFallAcceleration() throws Exception {
        ParticleLoader particleLoader = new ParticleLoader(new File(FREE_FALL_PATH));
        List<Particle> particleList = new ArrayList<>();

        particleLoader.load(particleList::add);

        assertThat(particleList).hasSize(1);

        double yVelocity = particleList.get(0)
                .getCurrentIteration()
                .getAcceleration()
                .getY();

        assertThat(yVelocity).isEqualTo(-9.8);

    }

    @Test
    public void load_freeFallParticle_returnsOneParticleWithZeroPositionAndZeroVelocity() throws Exception {
        ParticleLoader particleLoader = new ParticleLoader(new File(FREE_FALL_PATH));
        List<Particle> particleList = new ArrayList<>();

        particleLoader.load(particleList::add);

        assertThat(particleList).hasSize(1);

        Iteration currentIteration = particleList.get(0)
                .getCurrentIteration();

        assertThat(currentIteration.getPosition().getX()).isEqualTo(0.0);
        assertThat(currentIteration.getPosition().getY()).isEqualTo(0.0);
        assertThat(currentIteration.getPosition().getZ()).isEqualTo(0.0);
        assertThat(currentIteration.getVelocity().getX()).isEqualTo(0.0);
        assertThat(currentIteration.getVelocity().getY()).isEqualTo(0.0);
        assertThat(currentIteration.getVelocity().getZ()).isEqualTo(0.0);

    }

    @Test
    public void load_latticeParticle_returnsOneParticleWithFreeFallAcceleration() throws Exception {
        ParticleLoader particleLoader = new ParticleLoader(new File(LATTICE_PATH));
        List<Particle> particleList = new ArrayList<>();

        particleLoader.load(particleList::add);

        assertThat(particleList).hasSize(4);
        assertThat(particleList)
                .extracting(Particle::getCurrentIteration)
                .extracting(Iteration::getPosition)
                .extracting(Vector::getX)
                .containsExactlyInAnyOrder(0.0, 1.0, 0.0, 1.0);

        assertThat(particleList)
                .extracting(Particle::getCurrentIteration)
                .extracting(Iteration::getPosition)
                .extracting(Vector::getY)
                .containsExactlyInAnyOrder(0.0, 1.0, 0.0, 1.0);
    }

}