package com.mds.web.controller;

import com.mds.data.particle.Particle;
import com.mds.data.particle.ParticleRepository;
import com.mds.service.create.ParticleService;
import com.mds.service.runner.IntegrationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RestController
public class ParticleController {

    private final IntegrationRunner integrationRunner;

    private final ParticleService particleService;

    @Autowired
    public ParticleController(final IntegrationRunner integrationRunner, final ParticleService particleService) {
        this.integrationRunner = integrationRunner;
        this.particleService = particleService;
    }

    @CrossOrigin
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/particles/{iterations}")
    public Flux<ParticleResponse> getParticles(@PathVariable(name = "iterations") final int iterations) {

        List<Flux<ParticleResponse>> fluxes = IntStream.range(1, iterations)
                .mapToObj(value -> integrate())
                .collect(toList());

        fluxes.add(Flux.just(ParticleResponse.complete()));
        return Flux.concat(fluxes);
    }

    @CrossOrigin
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/particles")
    public Flux<ParticleResponse> getStartLocations() {
        List<Flux<ParticleResponse>> fluxes = Arrays.asList(
                particleService.findAll()
                        .map(ParticleResponse::inComplete),
                Flux.just(ParticleResponse.complete())
        );

        return Flux.concat(fluxes);
    }

    private Flux<ParticleResponse> integrate() {
        return integrationRunner.run()
                .map(ParticleResponse::inComplete);
    }

}
