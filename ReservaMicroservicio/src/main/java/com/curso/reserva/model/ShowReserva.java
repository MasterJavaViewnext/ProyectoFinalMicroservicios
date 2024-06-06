package com.curso.reserva.model;

import java.util.Objects;

/**
 * Clase que sirve de modelo para poder mostrar el origen, destino y nombre del hotel 
 * en la vista lista de reservas
 * @author Raul Rodal
 *
 */
public class ShowReserva {
	private Long idReserva;
	private Long idCliente;
	private Long idHotel;
	private Long idVuelo;
	private int numPersonas;
	
	private String nombreHotel;
	private String destinoVuelo;
	private String origenVuelo;
	
	public ShowReserva() {
	}

	public ShowReserva(Long idCliente, Long idHotel, Long idVuelo, int numPersonas) {
		this.idCliente = idCliente; 
		this.idHotel = idHotel;
		this.idVuelo = idVuelo;
		this.numPersonas = numPersonas;
	}
	
	public ShowReserva(Long idReserva, Long idCliente, Long idHotel, Long idVuelo, int numPersonas) {
		this.idReserva = idReserva;
		this.idCliente = idCliente;
		this.idHotel = idHotel;
		this.idVuelo = idVuelo;
		this.numPersonas = numPersonas;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	public Long getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(Long idVuelo) {
		this.idVuelo = idVuelo;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public String getDestinoVuelo() {
		return destinoVuelo;
	}

	public void setDestinoVuelo(String destinoVuelo) {
		this.destinoVuelo = destinoVuelo;
	}

	public String getOrigenVuelo() {
		return origenVuelo;
	}

	public void setOrigenVuelo(String origenVuelo) {
		this.origenVuelo = origenVuelo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idReserva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowReserva other = (ShowReserva) obj;
		return Objects.equals(idReserva, other.idReserva);
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", idCliente=" + idCliente + ", idHotel=" + idHotel + ", idVuelo="
				+ idVuelo + ", numPersonas=" + numPersonas + "]";
	}
	
	
}
