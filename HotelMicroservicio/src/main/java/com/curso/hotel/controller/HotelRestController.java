package com.curso.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.hotel.model.Hotel;
import com.curso.hotel.service.HotelService;

@RestController
@RequestMapping("hoteles")
public class HotelRestController {

	@Autowired
	HotelService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hotel findById(@PathVariable long id) {
		return service.findById(id).orElse(null);
	}
	
	@GetMapping(value = "pais/{pais}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> findAll(@PathVariable String pais) {
		return service.findByPais(pais);
	}

	/**
	 * Endpoint que devuelve si el hotel con el id proporcionado está disponible
	 * 
	 * @param  idHotel
	 * @return boolean disponible
	 */
	@GetMapping(value = "disponibles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean findDisponible(@PathVariable long id){
		return service.hotelDisponible(id);
	}
	
	/**
	 * Endpoint que devuelve todos los hoteles disponibles
	 * 
	 * @return lista de hoteles disponibles
	 */
	@GetMapping(value = "disponibles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> findDisponibles(){
		return service.findByDisponibleTrue();
	}
	
	/**
	 * Endpoint que devuelve un hotel buscandolo un nombre proporcionado
	 * 
	 * @param 	nombre del hotel a buscar
	 * @return	hotel con el mismo nombre
	 */
	@GetMapping(value = "nombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hotel findByNombre(@PathVariable String nombre){
		return service.findByNombre(nombre);
	}
}
