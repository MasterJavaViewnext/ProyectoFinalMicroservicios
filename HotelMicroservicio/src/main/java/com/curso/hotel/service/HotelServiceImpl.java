package com.curso.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.hotel.dao.HotelDao;
import com.curso.hotel.model.Hotel;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelDao dao;

	@Override
	public List<Hotel> findAll() {
		return dao.findAll();
	}
	
	@Override
	public List<Hotel> findByPais(String pais) {
		return dao.findByPais(pais);
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public List<Hotel> findByDisponibleTrue() {
		return dao.findByDisponibleTrue();
	}

	@Override
	public Hotel findByNombre(String nombre) {
		return dao.findByNombre(nombre).get(0);
	}

	//Método que devuelve si el hotel con el id proporcionado está disponible
	@Override
	public boolean hotelDisponible(long id) {
		Hotel hotel = findById(id).orElse(null);
		return findByDisponibleTrue().contains(hotel);
	}

}
