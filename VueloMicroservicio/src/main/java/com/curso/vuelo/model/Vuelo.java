package com.curso.vuelo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vuelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVuelo;
	private String companhia;
	private LocalDate fechaVuelo;
	private String origen;
	private String destino;
	private double precio;
	private int plazasDisponibles;
	
	public Vuelo() {
	}
	
	public Vuelo(Long idVuelo, String companhia, LocalDate fechaVuelo, double precio, int plazasDisponibles) {
		this.idVuelo = idVuelo;
		this.companhia = companhia;
		this.fechaVuelo = fechaVuelo;
		this.precio = precio;
		this.plazasDisponibles = plazasDisponibles;
	}

	public Vuelo(Long idVuelo, String companhia, LocalDate fechaVuelo, String origen, String destino, double precio, int plazasDisponibles) {
		this.idVuelo = idVuelo;
		this.companhia = companhia;
		this.fechaVuelo = fechaVuelo;
		this.origen = origen;
		this.destino = destino;
		this.precio = precio;
		this.plazasDisponibles = plazasDisponibles;
	}

	public Long getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(Long idVuelo) {
		this.idVuelo = idVuelo;
	}

	public String getCompanhia() {
		return companhia;
	}

	public void setCompanhia(String companhia) {
		this.companhia = companhia;
	}

	public LocalDate getFechaVuelo() {
		return fechaVuelo;
	}

	public void setFechaVuelo(LocalDate fechaVuelo) {
		this.fechaVuelo = fechaVuelo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}
}
