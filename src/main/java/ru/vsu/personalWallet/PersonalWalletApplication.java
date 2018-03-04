package ru.vsu.personalWallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.vsu.personalWallet")
@SpringBootApplication
public class PersonalWalletApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalWalletApplication.class, args);
    }
}