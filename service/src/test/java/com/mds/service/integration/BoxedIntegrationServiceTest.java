package com.mds.service.integration;

import com.mds.data.math.Iteration;
import com.mds.data.math.Vector;
import com.mds.data.particle.Particle;
import com.mds.service.integration.box.Box;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BoxedIntegrationServiceTest {

    @Mock
    private Box box;

    @Mock
    private Particle particle;

    @Mock
    private Vector particlePosition;

    private BoxedIntegrationService boxedIntegrationService;

    @Before
    public void setUp() {
        IntegrationService integrationService = mock(IntegrationService.class);
        given(integrationService.iterate(any()))
                .willReturn(particle);

        Iteration nextIteration = mock(Iteration.class);
        given(nextIteration.getPosition())
                .willReturn(particlePosition);
        given(particle.getNextIteration())
                .willReturn(nextIteration);

        boxedIntegrationService = new BoxedIntegrationService(integrationService, box);
    }

    @Test
    public void iterate_ParticleInBox_returnsSameParticle() throws Exception {
        given(box.planesTraversed(particlePosition))
                .willReturn(Collections.emptyList());

        Particle iterate = boxedIntegrationService.iterate(particle);

        assertThat(iterate)
                .isSameAs(particle);
    }
}