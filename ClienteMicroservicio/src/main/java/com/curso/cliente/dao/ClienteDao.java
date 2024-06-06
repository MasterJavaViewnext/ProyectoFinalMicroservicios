package com.curso.cliente.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.curso.cliente.model.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

	//Query para comprobar que existe ese usuario logeado y obtenerlo
	@Query("SELECT c FROM Cliente c WHERE c.user = ?1 AND c.password = ?2")
	Optional<Cliente> checkUser(String user, String password);

}
