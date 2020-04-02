package com.javamentor.slack.bot.chit;

import com.javamentor.slack.bot.chit.initializer.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EntityScan("com.javamentor.slack.bot.chit.models")
@EnableTransactionManagement
@EnableJpaRepositories(value = {"com.javamentor.slack.bot.chit.repository", "com.javamentor.slack.bot.chit.repository.impl"})
public class MainApp {

    @Bean(initMethod = "init")
	@PostConstruct
    public Initializer init() {
        return new Initializer();
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

}
