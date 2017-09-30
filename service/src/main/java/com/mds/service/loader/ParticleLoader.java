package com.mds.service.loader;


import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mds.data.math.Iteration;
import com.mds.data.math.Vector;
import com.mds.data.particle.Particle;
import com.mds.service.exception.ServiceLayerException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class ParticleLoader {

    private final CsvMapper csvMapper = new CsvMapper();
    private final CsvSchema csvSchema = csvMapper.schemaFor(ParticleCsvSchema.class);
    private final File file;

    public ParticleLoader(final File file) {
        this.file = file;
    }

    public void load(final Consumer<Particle> particleConsumer) {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.lines()
                    .map(this::marshallParticle)
                    .map(this::mapParticle)
                    .forEach(particleConsumer);

        } catch (IOException e) {
            throw new ServiceLayerException("Error reading from file " + file.getAbsolutePath(), e);
        }
    }

    private ParticleCsvSchema marshallParticle(final String csvLine) {
        try {
            return csvMapper
                    .readerFor(ParticleCsvSchema.class)
                    .with(csvSchema)
                    .readValue(csvLine);
        } catch (IOException e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    private Particle mapParticle(final ParticleCsvSchema particleCsvSchema) {
        Vector position = new Vector(particleCsvSchema.getX(), particleCsvSchema.getY(), particleCsvSchema.getZ());
        Vector velocity = new Vector(particleCsvSchema.getVx(), particleCsvSchema.getVy(), particleCsvSchema.getVz());
        Vector acceleration = new Vector(particleCsvSchema.getAx(), particleCsvSchema.getAy(), particleCsvSchema.getAz());

        Iteration currentIteration = new Iteration(position, velocity, acceleration);
        return new Particle(currentIteration, null);
    }

}
