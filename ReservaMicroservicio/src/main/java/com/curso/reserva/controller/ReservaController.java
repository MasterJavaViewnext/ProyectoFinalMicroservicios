package com.curso.reserva.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.curso.reserva.model.ClienteDTO;
import com.curso.reserva.model.HotelDTO;
import com.curso.reserva.model.Reserva;
import com.curso.reserva.model.ShowReserva;
import com.curso.reserva.model.VueloDTO;
import com.curso.reserva.service.ReservaService;
import com.curso.reserva.service.ReservaServiceImpl;

import jakarta.servlet.http.HttpSession;

/**
 * Controller para las vistas de reservas CRUD
 * @author Raul Rodal
 *
 */
@Controller
@RequestMapping("reserva")
public class ReservaController {

	@Autowired
	RestTemplate template;
	
	@Autowired
	ReservaService service;

	/**
	 * Método que  muestra las reservas de ese cliente
	 * @param 	idCliente
	 * @param 	model
	 * @return 	vista reservas.html con la lista de Reservas del cliente
	 */
	@GetMapping(value = "{idCliente}")
	public String vistaReservas(Model model, HttpSession session, @PathVariable long idCliente) {
		//Obetenemos el cliente a partir de su id y lo añadimos al model
		ClienteDTO cliente = template.getForObject(ReservaServiceImpl.URL_CLIENTES+"/"+idCliente, ClienteDTO.class);
		model.addAttribute("cliente", cliente);
		//Transformamos la lista de reservas en ShowReservas y añadimos la lista de showReservas de ese cliente al model
		List<ShowReserva> reservas = listaReservasToListaShowReservas(service.findByIdCliente(idCliente));
		model.addAttribute("reservas", reservas);
		//Instanciamos reserva para añadir el idCliente y la añadimos al model
		Reserva reserva = new Reserva();
		reserva.setIdCliente(cliente.getId());
		session.setAttribute("reserva", reserva);
		return "reservas";
	}
	
	/**
	 * Método que devuelve los detalles de la reserva seleccionada obteneniendo
	 *  cada uno de los objetos que necesitamos ver en el detalle de reserva y 
	 *  añadiendolos al model
	 *  
	 * @param 	model
	 * @param 	idReserva
	 * @return	detalleReserva.html
	 */
	@GetMapping("detalle/{idReserva}")
	public String detalleReserva(Model model, @PathVariable Long idReserva) {
		Reserva reserva = service.findById(idReserva);
		HotelDTO hotel = template.getForObject(ReservaServiceImpl.URL_HOTELES+"/"+reserva.getIdHotel(), HotelDTO.class);
		VueloDTO vuelo = template.getForObject(ReservaServiceImpl.URL_VUELOS+"/"+reserva.getIdVuelo(), VueloDTO.class);
		model.addAttribute("reserva", reserva);
		model.addAttribute("hotel", hotel);
		model.addAttribute("vuelo", vuelo);
		return "detalleReserva";
	}
	
	/**
	 * Método que elimina una reserva a partir de un id prorpocionado y devuelve la vista principal
	 * 
	 * @param 	model
	 * @param 	session
	 * @param 	idReserva a eliminar
	 * @return	vistaReservas
	 */
	@GetMapping(value = "eliminar/{idReserva}")
	public String deleteReserva(Model model, HttpSession session, @PathVariable("idReserva") long idReserva) {
		service.deleteById(idReserva);
		Reserva reserva = (Reserva) session.getAttribute("reserva");
		return vistaReservas(model, session, reserva.getIdCliente());
	}
	
	/**
	 * Endpoint que devuelve el formulario de numpersonas para que es el primer paso
	 * en el alta de una reserva
	 * 
	 * @param 	model
	 * @param 	session
	 * @return	numPersonas.html
	 */
	@GetMapping("insert")
	public String selectNumPersonas(Model model, HttpSession session) {
		model.addAttribute("reserva", session.getAttribute("reserva"));
		return "numPersonas";
	}
	
	/**
	 * Maneja las solicitudes GET para la URL "/vuelos".
	 * 
	 * Este método recupera una lista de vuelos disponibles según el número de personas,
	 * actualiza la sesión con el número de personas para la reserva actual,
	 * y añade la lista de vuelos al modelo para ser mostrada en la vista selectVuelo.html.
	 *
	 * @param model       el objeto Model utilizado para pasar datos a la vista
	 * @param session     el objeto HttpSession para acceder y modificar los atributos de la sesión
	 * @param reserva     el objeto Reserva vinculado desde el atributo del modelo
	 * @param numPersonas el número de personas para las cuales se buscan vuelos disponibles, obtenido del parámetro de la solicitud
	 * @return            el nombre de la vista a renderizar, selectVuelo.html
	 */
	@GetMapping("vuelos")
	public String selectVuelo(Model model, HttpSession session, @ModelAttribute("reserva") Reserva reserva, @RequestParam("numPersonas") int numPersonas) {
		List<VueloDTO> vuelos = Arrays.asList(template.getForObject(ReservaServiceImpl.URL_VUELOS+"/plazas/"+numPersonas, VueloDTO[].class));
		model.addAttribute("vuelos", vuelos);
		((Reserva)session.getAttribute("reserva")).setNumPersonas(reserva.getNumPersonas());
		return "selectVuelo";
	}
	
	/**
	 * Maneja las solicitudes POST para la URL "/insert/{idVuelo}".
	 * 
	 * Este método recupera los detalles de un vuelo específico y una lista de hoteles disponibles en el destino del vuelo,
	 * actualiza la sesión con el ID del vuelo seleccionado para la reserva actual,
	 * y añade la lista de hoteles al modelo para ser mostrada en la vista selectHotel.html
	 *
	 * @param model   el objeto Model utilizado para pasar datos a la vista
	 * @param session el objeto HttpSession para acceder y modificar los atributos de la sesión
	 * @param idVuelo el ID del vuelo seleccionado, obtenido de la variable de ruta
	 * @return        el nombre de la vista a renderizar, selectHotel.html
	 */
	@PostMapping("insert/{idVuelo}")
	public String selectHotel(Model model, HttpSession session, @PathVariable Long idVuelo) {
		VueloDTO vuelo = template.getForObject(ReservaServiceImpl.URL_VUELOS + "/" + idVuelo, VueloDTO.class);
		List<HotelDTO> hoteles = Arrays.asList(template.getForObject(ReservaServiceImpl.URL_HOTELES+"/pais/"+vuelo.getDestino(), HotelDTO[].class));
		model.addAttribute("hoteles", hoteles);

		Reserva reserva = (Reserva) session.getAttribute("reserva");
		reserva.setIdVuelo(idVuelo);
		session.setAttribute("reserva", reserva);
		return "selectHotel";
	}
	
	/**
	 * Maneja las solicitudes POST para la URL "/insertada/{idHotel}".
	 * 
	 * Este método actualiza la reserva actual en la sesión con el ID del hotel seleccionado,
	 * inserta la reserva actualizada en el sistema de reservas, 
	 * y añade la reserva al modelo para ser mostrada en la vista showReserva.html
	 *
	 * @param model   el objeto Model utilizado para pasar datos a la vista
	 * @param session el objeto HttpSession para acceder y modificar los atributos de la sesión
	 * @param idHotel el ID del hotel seleccionado, obtenido de la variable de ruta
	 * @return        el nombre de la vista a renderizar, showReserva.html
	 */
	@PostMapping("insertada/{idHotel}")
	public String insertada(Model model, HttpSession session, @PathVariable Long idHotel) {
		Reserva reserva = (Reserva) session.getAttribute("reserva");
		reserva.setIdHotel(idHotel);
		service.insert(reserva);
		model.addAttribute("reserva", reserva);
		return "showReserva";
	}
	
	
	private List<ShowReserva> listaReservasToListaShowReservas(List<Reserva> reservas){
		return reservas.stream().map(r -> {
			ShowReserva reserva = new ShowReserva(r.getIdReserva(), r.getIdCliente(), r.getIdHotel(), r.getIdVuelo(), r.getNumPersonas());
			reserva.setNombreHotel((template.getForObject(ReservaServiceImpl.URL_HOTELES+"/"+r.getIdHotel(), HotelDTO.class)).getNombre());
			reserva.setOrigenVuelo((template.getForObject(ReservaServiceImpl.URL_VUELOS+"/"+r.getIdVuelo(), VueloDTO.class)).getOrigen());
			reserva.setDestinoVuelo((template.getForObject(ReservaServiceImpl.URL_VUELOS+"/"+r.getIdVuelo(), VueloDTO.class)).getDestino());
			return reserva;
		}).toList();
	}
}
