package com.curso.cliente.controller;

import org.springframework.http.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.cliente.model.Cliente;
import com.curso.cliente.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
	
	@Autowired
	ClienteService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> findAll(){
		return service.findAll();
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente findById(@PathVariable long id) {
		return service.findById(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Cliente cliente) {
		service.insert(cliente);
	}
	
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
}
