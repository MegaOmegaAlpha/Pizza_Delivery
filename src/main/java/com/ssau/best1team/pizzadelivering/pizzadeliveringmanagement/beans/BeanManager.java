package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.beans;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanManager {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
