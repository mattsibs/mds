package com.mds.web;

import com.mds.service.create.ParticleStartupService;
import com.mds.service.runner.IntegrationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan
@EnableReactiveMongoRepositories("com.mds")
public class Application implements CommandLineRunner {

    @Autowired
    private ParticleStartupService particleStartupService;

    @Autowired
    private IntegrationRunner integrationRunner;

    public static void main(final String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        particleStartupService.initializeParticles();
    }
}
