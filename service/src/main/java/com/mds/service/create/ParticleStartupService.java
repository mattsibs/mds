package com.mds.service.create;


import com.mds.data.particle.ParticleRepository;
import com.mds.service.loader.ParticleLoader;

public class ParticleStartupService {

    private final ParticleRepository particleRepository;
    private final ParticleLoader particleLoader;

    public ParticleStartupService(final ParticleRepository particleRepository,
                                  final ParticleLoader particleLoader) {
        this.particleRepository = particleRepository;
        this.particleLoader = particleLoader;
    }

    public void initializeParticles() {
//        particleRepository.deleteAll().block();
//
//        particleLoader.load(particle ->
//                particleRepository.save(particle)
//                        .doOnNext(System.out::println)
//                        .block()
//        );
//
//        System.out.println();
    }
}
