package com.mds.web.controller;

import com.mds.data.particle.Particle;
import com.mds.data.particle.ParticleRepository;
import com.mds.service.runner.IntegrationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RestController
public class ParticleController {

    @Autowired
    private IntegrationRunner integrationRunner;

    @Autowired
    private ParticleRepository particleRepository;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE, value = "/particles/{iterations}")
    public Flux<Particle> getParticles(@PathVariable(name = "iterations") final int iterations) {

        List<Flux<Particle>> fluxes = IntStream.range(1, iterations)
                .mapToObj(value -> integrationRunner.run())
                .collect(toList());

        return Flux.concat(fluxes);
    }

}
