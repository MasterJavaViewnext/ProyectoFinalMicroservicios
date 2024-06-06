package com.curso.vuelo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.curso.vuelo.model.Vuelo;

public interface VueloDao extends JpaRepository<Vuelo, Long> {
	
	List<Vuelo> findByPlazasDisponiblesGreaterThan(int plazas);
	
	//Query que resta las plazas indicadas al vuelo indicado
	@Transactional
	@Modifying
	@Query("UPDATE Vuelo v SET v.plazasDisponibles = v.plazasDisponibles - ?2 WHERE v.idVuelo = ?1")
	void restarPlazas(long id, int plazas);
}
