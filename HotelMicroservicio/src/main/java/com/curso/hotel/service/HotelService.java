package com.curso.hotel.service;

import java.util.List;
import java.util.Optional;

import com.curso.hotel.model.Hotel;

public interface HotelService {
	List<Hotel> findAll();
	List<Hotel> findByPais(String pais);
	Optional<Hotel> findById(Long id);
	Hotel findByNombre(String nombre);
	List<Hotel> findByDisponibleTrue();
	boolean hotelDisponible(long id);
}
