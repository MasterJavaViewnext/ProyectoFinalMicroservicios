package com.curso.vuelo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.vuelo.dao.VueloDao;
import com.curso.vuelo.model.Vuelo;

@Service
public class VueloServiceImpl implements VueloService {

	@Autowired
	VueloDao dao;
	
	@Override
	public List<Vuelo> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Vuelo> findById(long id) {
		return dao.findById(id);
	}

	@Override
	public List<Vuelo> findByPlazasDisponiblesGreaterThan(int plazas) {
		return dao.findByPlazasDisponiblesGreaterThan(plazas-1);
	}

	//Método que comprueba si hay suficientes plazas para asignarlas y si puede las resta
	@Override
	public boolean restarPlazas(long id, int plazas) {
		boolean realizado;
		Vuelo vuelo = findById(id).orElse(null);
			if (vuelo != null && plazas <= vuelo.getPlazasDisponibles()) {
				dao.restarPlazas(id, plazas);
				realizado = true;
			} else {
				realizado = false;
			}
			
		return realizado;
	}

}
