package com.java.goldmedalmetrics;

import com.java.goldmedalmetrics.model.Country;
import com.java.goldmedalmetrics.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoldMedalMetricsApplication {

    public static Logger log = LoggerFactory.getLogger(GoldMedalMetricsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GoldMedalMetricsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CountryRepository countryRepository) {

        return (args) -> {

            // fetch all customers
            int count = 10;
            log.info("Countries found with findAll():");
            log.info("-------------------------------");
            for (Country country : countryRepository.findAll()) {
                log.info(country.toString());
                count++;
                if(count > 15)
                    break;
            }
            log.info("");

        };
    }
}
