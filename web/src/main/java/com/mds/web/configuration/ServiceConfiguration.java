package com.mds.web.configuration;

import com.mds.data.math.DeltaTime;
import com.mds.data.math.Plane;
import com.mds.data.math.TimeUnit;
import com.mds.data.math.Vector;
import com.mds.service.create.ParticleService;
import com.mds.service.create.ParticleStartupService;
import com.mds.service.integration.BoxedIntegrationService;
import com.mds.service.integration.IntegrationService;
import com.mds.service.integration.NonInteractingIntegrationService;
import com.mds.service.integration.box.Box;
import com.mds.service.loader.ParticleLoader;
import com.mds.service.runner.IntegrationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

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
    public IntegrationService integrationService() {
        NonInteractingIntegrationService nonInteractingIntegrationService = new NonInteractingIntegrationService(new DeltaTime(1, TimeUnit.MILLI));

        final List<Plane> planes = Arrays.asList(
                new Plane(new Vector(1.0, 0.0, 0.0), 5.0),
                new Plane(new Vector(-1.0, 0.0, 0.0), 5.0),
                new Plane(new Vector(0.0, 1.0, 0.0), 5.0),
                new Plane(new Vector(0.0, -1.0, 0.0), 5.0),
                new Plane(new Vector(0.0, 0.0, 1.0), 5.0),
                new Plane(new Vector(0.0, 0.0, -1.0), 5.0)
        );

        return new BoxedIntegrationService(nonInteractingIntegrationService, new Box(planes));
    }

    @Bean
    public ParticleService particleService() {
        return new ParticleService(repositoryConfiguration.getParticleRepository());
    }

    @Bean
    public IntegrationRunner integrationRunner() {
        return new IntegrationRunner(integrationService(), particleService());
    }


}
