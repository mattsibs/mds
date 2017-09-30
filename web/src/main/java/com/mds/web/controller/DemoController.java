package com.mds.web.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
public class DemoController {

    @GetMapping("/events/{id}")
    public Mono<Event> getEvent(@PathVariable final long id) {
        return Mono.just(new Event(id));
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")
    public Flux<Event> getEvents() {
        Flux<Event> eventFlux = Flux.fromStream(
                Stream.generate(() -> new Event(System.currentTimeMillis()))
        );
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);

    }

    @Data
    @RequiredArgsConstructor
    public static class Event {
        private final long id;
    }

}
