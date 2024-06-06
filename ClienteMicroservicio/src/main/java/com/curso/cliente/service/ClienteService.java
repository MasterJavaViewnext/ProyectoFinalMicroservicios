package com.curso.cliente.service;

import java.util.List;

import com.curso.cliente.model.Cliente;

public interface ClienteService {
	List<Cliente> findAll();
	Cliente findById(long id);
	void insert(Cliente cliente);
	void delete(long id);
	Cliente checkUser(String user, String password);
}
