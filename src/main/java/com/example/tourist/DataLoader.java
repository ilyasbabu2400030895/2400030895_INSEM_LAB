package com.example.tourist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            TouristRepository repository) {

        return args -> {
            repository.save(
                    new Tourist("Ravi", "India"));

            repository.save(
                    new Tourist("John", "USA"));

            repository.save(
                    new Tourist("Aisha", "UAE"));
        };
    }
}