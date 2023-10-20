package com.gerenciamentofaculdade.gerenciamentofaculdade.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.SimpleDateFormat;

@Configuration
@EnableJpaRepositories("com.gerenciamentofaculdade")
class Config {
    // A minha classe ErrorResponse usa esse tipo de formato, decidi então deixar esse Bean já no gatilho pra não
    // ter que ficar configurando
    @Bean
    ObjectMapper objectMapper() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(df);
        return mapper;
    }
}