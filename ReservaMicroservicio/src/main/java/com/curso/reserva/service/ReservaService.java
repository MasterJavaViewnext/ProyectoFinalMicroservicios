package com.curso.reserva.service;

import java.util.List;

import com.curso.reserva.model.Reserva;

public interface ReservaService {
	List<Reserva> findAll();
	Reserva findById(long id);
	boolean insert(Reserva reserva);
	void deleteById(long id);
	List<Reserva> findByHotelNombre(String nombre);
	List<Reserva> findByIdCliente (long idCliente);
}
