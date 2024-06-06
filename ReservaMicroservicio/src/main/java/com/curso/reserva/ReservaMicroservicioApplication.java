package com.curso.reserva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EntityScan("com.curso.reserva.model")
@EnableJpaRepositories("com.curso.reserva.dao")
@SpringBootApplication(scanBasePackages = {"com.curso.reserva.controller", "com.curso.reserva.service"})
public class ReservaMicroservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservaMicroservicioApplication.class, args);
	}
	
	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}
}
