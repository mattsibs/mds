package com.mds.web.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class PropertiesConfiguration {
    @Value("${startup.particle.file}")
    private File particleFilePath;

    public File getParticleFilePath() {
        return particleFilePath;
    }
}
