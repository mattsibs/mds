package com.mds.web.configuration;

import com.mds.data.math.DeltaTime;
import com.mds.data.math.TimeUnit;
import com.mds.service.create.ParticleService;
import com.mds.service.create.ParticleStartupService;
import com.mds.service.integration.NonInteractingIntegrationService;
import com.mds.service.loader.ParticleLoader;
import com.mds.service.runner.IntegrationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PropertiesConfiguration.class, ApplicationConfiguration.class, RepositoryConfiguration.class})
public class ServiceConfiguration {

    @Autowired
    private PropertiesConfiguration propertiesConfiguration;

    @Autowired
    private RepositoryConfiguration repositoryConfiguration;

    @Bean
    public ParticleLoader particleLoader() {
        return new ParticleLoader(propertiesConfiguration.getParticleFilePath());
    }

    @Bean
    public ParticleStartupService particleStartupService() {
        return new ParticleStartupService(repositoryConfiguration.getParticleRepository(), particleLoader());
    }

    @Bean
    public NonInteractingIntegrationService nonInteractingIntegrationService() {
        return new NonInteractingIntegrationService(new DeltaTime(1, TimeUnit.MILLI));
    }

    @Bean
    public ParticleService particleService() {
        return new ParticleService(repositoryConfiguration.getParticleRepository());
    }

    @Bean
    public IntegrationRunner integrationRunner() {
        return new IntegrationRunner(nonInteractingIntegrationService(), particleService());
    }


}
