package com.mds.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mds.data.particle.Particle;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticleResponse {

    @JsonProperty
    private final Particle particle;
    @JsonProperty
    private final boolean isComplete;

    private ParticleResponse(final Particle particle, final boolean isComplete) {
        this.particle = particle;
        this.isComplete = isComplete;
    }

    public static ParticleResponse inComplete(final Particle particle) {
        return new ParticleResponse(particle, false);
    }

    public static ParticleResponse complete() {
        return new ParticleResponse(null, true);
    }
}
