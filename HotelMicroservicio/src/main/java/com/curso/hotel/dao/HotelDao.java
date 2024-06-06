package com.curso.hotel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.hotel.model.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Long>{

	List<Hotel> findByDisponibleTrue();
	List<Hotel> findByPais(String pais);
	List<Hotel> findByNombre(String nombre);
}
