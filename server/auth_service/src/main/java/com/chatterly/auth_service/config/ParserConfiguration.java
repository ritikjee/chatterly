package com.chatterly.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua_parser.Parser;

@Configuration
public class ParserConfiguration {

    @Bean
    public Parser uaParser() {
        return new Parser();
    }
}
