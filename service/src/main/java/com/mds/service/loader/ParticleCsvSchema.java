package com.mds.service.loader;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(value = {"x", "y", "z", "vx", "vy", "vz", "ax", "ay", "az"})
public class ParticleCsvSchema {

    @JsonProperty
    private double x;
    @JsonProperty
    private double y;
    @JsonProperty
    private double z;
    @JsonProperty
    private double vx;
    @JsonProperty
    private double vy;
    @JsonProperty
    private double vz;
    @JsonProperty
    private double ax;
    @JsonProperty
    private double ay;
    @JsonProperty
    private double az;
}
