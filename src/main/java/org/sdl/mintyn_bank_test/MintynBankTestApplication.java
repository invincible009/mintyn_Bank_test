package org.sdl.mintyn_bank_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MintynBankTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MintynBankTestApplication.class, args);
    }

}
