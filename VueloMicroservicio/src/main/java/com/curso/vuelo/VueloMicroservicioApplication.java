package com.curso.vuelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.curso.vuelo.model")
@EnableJpaRepositories("com.curso.vuelo.dao")
@SpringBootApplication(scanBasePackages = {"com.curso.vuelo.controller", "com.curso.vuelo.service"})
public class VueloMicroservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueloMicroservicioApplication.class, args);
	}

}
