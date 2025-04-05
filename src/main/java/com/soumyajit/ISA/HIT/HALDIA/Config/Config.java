package com.soumyajit.ISA.HIT.HALDIA.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public org.modelmapper.ModelMapper getbean(){
        return new org.modelmapper.ModelMapper();
    }

}
