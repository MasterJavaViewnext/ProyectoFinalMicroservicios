package com.curso.reserva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.curso.reserva.dao.ReservaDao;
import com.curso.reserva.model.HotelDTO;
import com.curso.reserva.model.Reserva;

@Service
public class ReservaServiceImpl implements ReservaService {

	public final static String URL_VUELOS = "http://localhost:8081/vuelos";
	public final static String URL_HOTELES = "http://localhost:8080/hoteles";
	public final static String URL_CLIENTES = "http://localhost:8083/clientes";

	@Autowired
	ReservaDao dao;
	@Autowired
	RestTemplate template;

	@Override
	public List<Reserva> findAll() {
		return dao.findAll();
	}

	@Override
	public Reserva findById(long id) {
		return dao.findById(id).orElse(null);
	}

	/**
	 * Método que comprueba si hay suficientes plazas para realizar la reserva y si el hotel
	 * está disponible. Si es así actualiza las plazas del vuelo e inserta a reserva.
	 * 
	 * @param Reserva reserva
	 * @return boolean realizado
	 */
	@Override
	public boolean insert(Reserva reserva) {
		boolean realizado;
		// Creacion de la URI con los request param
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_VUELOS)
				.queryParam("id", reserva.getIdVuelo())
				.queryParam("plazas", reserva.getNumPersonas());
		String url = builder.toUriString();
		
		if (hayPlazasVuelo(url) && hotelDisponible(reserva.getIdHotel())) {
			//Actualizacion de plazas del vuelo
			template.put(url, null);
			realizado = true;
			//Insercion de la reserva
			dao.save(reserva);
		} else {
			realizado = false;
		}
		return realizado;
	}

	@Override
	public void deleteById(long id) {
		dao.delete(findById(id));
	}

	/**
	 * Método que realiza una petición GET al microservicio de hotel y devuelve una lista de reservas
	 * que coincidan con el nombre del hotel proporcionado
	 * 
	 * @param nombre del hotel pra filtrar
	 * @return lista de reservas filtrada
	 */
	@Override
	public List<Reserva> findByHotelNombre(String nombre) {
		HotelDTO hotel = template.getForObject(URL_HOTELES+ "/nombre/" + nombre, HotelDTO.class);
		return dao.findByIdHotel(hotel.getIdHotel());
	}

	/**
	 * Método que realiza una peticion GET al microservicio de Vuelo 
	 * para comprobar si hay plazas disponibles
	 * 
	 * @param url 		String de la url con los parametros añadidos de id del vuelo y plazas requeridas
	 * @return boolean 	devuelve true si hay plazas suficientes y false si no las hay
	 */
	private boolean hayPlazasVuelo(String url) {
		// Obtención del codigo de estdo que devuelve esa peticion
		ResponseEntity<String> respuesta = template.getForEntity(url, String.class);
		return respuesta.getStatusCode() == HttpStatus.OK;
	}
	
	/**
	 * Método que realiza una peticion GET al microservicio de Hotel 
	 * para comprobar que el Hotel esté disponible para hacer una reserva
	 *  
	 * @param idHotel 	Long del id del hotel de la reserva 
	 * @return boolean	devuelve true si el hotel esta disponible y false en caso contrario
	 */
	private boolean hotelDisponible(long idHotel) {
		//Obtención de la disponibilidad del hotel
		return template.getForObject(URL_HOTELES+"/disponibles/"+idHotel, Boolean.class);
	}

	@Override
	public List<Reserva> findByIdCliente(long idCliente) {
		return dao.findByIdCliente(idCliente);
	}
}
