/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mds.web.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

import java.util.Collection;
import java.util.Collections;

@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@RequiredArgsConstructor
public class ApplicationConfiguration extends MongoConfigurationSupport {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(String.format("mongodb://localhost:%d", 27017));
    }

    @Override
    protected String getDatabaseName() {
        return "reactive";
    }

    protected Collection<String> getMappingBasePackages() {
        return Collections.singletonList("com.mds.data");
    }

    @Bean
    public ReactiveMongoOperations reactiveMongoTemplate() throws Exception {
        return new ReactiveMongoTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public ReactiveMongoDatabaseFactory mongoDbFactory() {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient(), getDatabaseName());
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception {

        MappingMongoConverter converter = new MappingMongoConverter(ReactiveMongoTemplate.NO_OP_REF_RESOLVER,
                mongoMappingContext());
        converter.setCustomConversions(customConversions());

        return converter;
    }
}
