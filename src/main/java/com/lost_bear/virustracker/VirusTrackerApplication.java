package com.lost_bear.virustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VirusTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirusTrackerApplication.class, args);
    }

}
