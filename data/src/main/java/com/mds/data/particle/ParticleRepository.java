package com.mds.data.particle;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticleRepository extends ReactiveCrudRepository<Particle, String> {

}
