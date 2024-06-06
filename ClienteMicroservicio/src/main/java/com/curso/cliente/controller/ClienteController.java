package com.curso.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.curso.cliente.model.Cliente;
import com.curso.cliente.service.ClienteService;

/**
 * Controlador para las vistas de la parte del login
 * @author Raul Rodal
 *
 */
@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
	/**
	 * Endpoint que muestra un formulario de login para acceder a las reservas 
	 * y crea un cliente vacio para alñadirlo al model
	 * @param 	model con un Cliente
	 * @return 	vista de login
	 */
	@GetMapping(value="login")
	public String login(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "login";
	}
	
	/**
	 * Método que comprueba que los datos introducidos están en la base de datos
	 * si la utenticacion es correcta el metodo redirecciona al controller de reservas
	 * para mostrar las reservas de es cliente
	 * 
	 * @param 	cliente regisstrado en la bbdd
	 * @return	Vista de la lista del cliente si se logea correctamente
	 */
	@GetMapping(value="entrar")
	public RedirectView entrar(@ModelAttribute("cliente") Cliente cliente) {
		Cliente clienteLoged = service.checkUser(cliente.getUser(), cliente.getPassword());
		if (clienteLoged != null) {
			return new RedirectView("http://localhost:8082/reserva/"+clienteLoged.getId());
		} else {
			return new RedirectView("login");
		}
	}
	
}
