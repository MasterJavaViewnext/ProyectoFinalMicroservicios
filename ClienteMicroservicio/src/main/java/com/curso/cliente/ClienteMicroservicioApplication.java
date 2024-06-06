package com.curso.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EntityScan("com.curso.cliente.model")
@EnableJpaRepositories("com.curso.cliente.dao")
@SpringBootApplication(scanBasePackages = {"com.curso.cliente.controller", "com.curso.cliente.service"})
public class ClienteMicroservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteMicroservicioApplication.class, args);
	}
	
	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}

}
