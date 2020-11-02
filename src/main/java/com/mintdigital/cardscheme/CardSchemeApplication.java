package com.mintdigital.cardscheme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding(Source.class)
public class CardSchemeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardSchemeApplication.class, args);
    }



}
