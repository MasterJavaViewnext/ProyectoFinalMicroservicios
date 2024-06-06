package com.curso.vuelo.service;

import java.util.List;
import java.util.Optional;

import com.curso.vuelo.model.Vuelo;

public interface VueloService {
	List<Vuelo> findAll();
	Optional<Vuelo> findById(long id);
	List<Vuelo> findByPlazasDisponiblesGreaterThan(int plazas);
	boolean restarPlazas(long id, int plazas);
}
