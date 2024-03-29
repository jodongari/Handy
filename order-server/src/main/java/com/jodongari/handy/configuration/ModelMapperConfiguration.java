package com.jodongari.handy.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        final ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMethodAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PROTECTED)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
