package com.mds.service.runner;

import com.mds.data.particle.Particle;
import com.mds.service.create.ParticleService;
import com.mds.service.integration.IntegrationService;
import org.apache.log4j.Logger;
import reactor.core.publisher.Flux;

public class IntegrationRunner {

    private static final Logger L = Logger.getLogger(IntegrationRunner.class);

    private final IntegrationService integrationService;
    private final ParticleService particleService;

    public IntegrationRunner(final IntegrationService integrationService,
                             final ParticleService particleService) {
        this.integrationService = integrationService;
        this.particleService = particleService;
    }

    public Flux<Particle> run() {
        return particleService.findAll()
                .parallel()
                .map(integrationService::iterate)
                .doOnNext(particleService::save)
                .sequential()
                .doOnComplete(this::updateParticles);
    }

    private Long updateParticles() {
        return particleService.findAll()
                .doOnNext(Particle::updateIteration)
                .doOnNext(particleService::save)
                .count()
                .block();
    }

}
