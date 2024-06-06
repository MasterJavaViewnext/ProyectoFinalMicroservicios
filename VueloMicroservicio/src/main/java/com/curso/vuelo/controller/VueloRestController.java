package com.curso.vuelo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.vuelo.model.Vuelo;
import com.curso.vuelo.service.VueloService;

@RestController
@RequestMapping("vuelos")
public class VueloRestController {

	@Autowired
	VueloService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> findAll(){
		return service.findAll();
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Vuelo finById(@PathVariable long id) {
		return service.findById(id).orElse(null);
	}
	
	/**
	 * Endpoint que devuelve todos los vuelos que tengan igual o mas plazas disponibles
	 * que las proporcionadas
	 * 
	 * @param 	plazas necesarias
	 * @return	lista de vuelos con plazas suficientes
	 */
	@GetMapping(value = "plazas/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> findAllWithPlazas(@PathVariable int plazas){
		return service.findByPlazasDisponiblesGreaterThan(plazas);
	}
	
	/**
	 * Método que resta las plazas proporcionadas al vuelo con el id proporcionado, en el caso de que 
	 * el vuelo no tenga suficientes plazas devolverá el codigo de estado 409, en caso contrario 201
	 * 
	 * @param 	id del vuelo
	 * @param 	plazas solicitadas
	 * @return 	ResponseEntity con codigo de estado
	 */
	@PutMapping
	public ResponseEntity<String> restarPlazas(@RequestParam long id, @RequestParam int plazas) {
		ResponseEntity<String>  ret;
		if (service.restarPlazas(id, plazas)) {
			ret = ResponseEntity.status(HttpStatus.CREATED).body ("HTTP STATUS 201: Plazas correctamente asignadas");
		} else {
			ret = ResponseEntity.status(HttpStatus.CONFLICT).body("HTTP STATUS 409: Plazas disponibles insuficientes");
		}
		return ret;
	}
}
