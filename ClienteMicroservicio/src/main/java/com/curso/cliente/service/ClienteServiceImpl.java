package com.curso.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.cliente.dao.ClienteDao;
import com.curso.cliente.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteDao dao;
	
	@Override
	public List<Cliente> findAll() {
		return dao.findAll();
	}

	@Override
	public Cliente findById(long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public void insert(Cliente cliente) {
		dao.save(cliente);
	}

	@Override
	public void delete(long id) {
		dao.delete(findById(id));
	}

	//Método para comprobar que el cliente logeado está en la bbdd
	@Override
	public Cliente checkUser(String user, String password) {
		return dao.checkUser(user, password).orElse(null);
	}

}
