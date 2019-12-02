package com.glinka.p2;

import com.glinka.p2.service.PdfService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class P2Application {

    public static void main(String[] args) {
        SpringApplication.run(P2Application.class, args);
    }

}
