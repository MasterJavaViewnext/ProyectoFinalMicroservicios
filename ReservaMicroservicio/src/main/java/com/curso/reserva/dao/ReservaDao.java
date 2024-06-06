package com.curso.reserva.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.reserva.model.Reserva;

public interface ReservaDao extends JpaRepository<Reserva, Long>{

	List<Reserva> findByIdHotel(Long idHotel);

	List<Reserva> findByIdCliente(long idCliente);

}
