package com.mathiasruck.wallet.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Configuration
public class JacksonConfig {

    @Bean(name = {"objectMapper"})
    @Primary
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder
                .serializationInclusion(NON_NULL)
                .failOnEmptyBeans(false)
                .failOnUnknownProperties(false)
                .featuresToEnable(ACCEPT_CASE_INSENSITIVE_PROPERTIES, ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
                .propertyNamingStrategy(SNAKE_CASE)
                .build();
    }
}
