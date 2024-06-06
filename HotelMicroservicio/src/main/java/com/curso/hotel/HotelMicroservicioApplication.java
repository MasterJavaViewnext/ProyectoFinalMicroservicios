package com.curso.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.curso.hotel.model")
@EnableJpaRepositories("com.curso.hotel.dao")
@SpringBootApplication(scanBasePackages = {"com.curso.hotel.controller", "com.curso.hotel.service"})
public class HotelMicroservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelMicroservicioApplication.class, args);
	}

}
