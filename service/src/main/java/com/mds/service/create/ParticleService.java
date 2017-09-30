package com.mds.service.create;


import com.mds.data.particle.Particle;
import com.mds.data.particle.ParticleRepository;
import reactor.core.publisher.Flux;

public class ParticleService {

    private final ParticleRepository particleRepository;

    public ParticleService(final ParticleRepository particleRepository) {
        this.particleRepository = particleRepository;
    }

    public Flux<Particle> findAll() {
        return particleRepository.findAll();
    }

    public Particle save(final Particle particle) {
        return particleRepository.save(particle).block();
    }
}
