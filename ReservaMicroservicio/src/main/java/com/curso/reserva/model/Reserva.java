package com.curso.reserva.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reserva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReserva;
	private Long idCliente;
	private Long idHotel;
	private Long idVuelo;
	private int numPersonas;
	
	public Reserva() {
	}

	public Reserva(Long idCliente, Long idHotel, Long idVuelo, int numPersonas) {
		this.idCliente = idCliente; 
		this.idHotel = idHotel;
		this.idVuelo = idVuelo;
		this.numPersonas = numPersonas;
	}
	
	public Reserva(Long idReserva, Long idCliente, Long idHotel, Long idVuelo, int numPersonas) {
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
		Reserva other = (Reserva) obj;
		return Objects.equals(idReserva, other.idReserva);
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", idCliente=" + idCliente + ", idHotel=" + idHotel + ", idVuelo="
				+ idVuelo + ", numPersonas=" + numPersonas + "]";
	}
	
	
}
