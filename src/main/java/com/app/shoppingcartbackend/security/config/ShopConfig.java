package com.app.shoppingcartbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Bean Defining
@Configuration
public class ShopConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
