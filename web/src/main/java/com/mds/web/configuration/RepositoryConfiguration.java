package com.mds.web.configuration;

import com.mds.data.particle.ParticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Autowired
    private ParticleRepository particleRepository;

    public ParticleRepository getParticleRepository() {
        return particleRepository;
    }
}