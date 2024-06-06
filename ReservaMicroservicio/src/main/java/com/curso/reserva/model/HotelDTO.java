package com.curso.reserva.model;

import java.util.Objects;

public class HotelDTO {
	private Long idHotel;
	private String nombre;
	private String localizacion;
	private String pais;
	private String categoria;
	private double precio;
	private boolean disponible;
	
	public HotelDTO() {
		
	}
	
	public HotelDTO(Long idHotel, String nombre, String categoria, double precio, boolean disponible) {
		this.idHotel = idHotel;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.disponible = disponible;
	}

	public HotelDTO(Long idHotel, String nombre, String localizacion, String pais, String categoria, double precio,
			boolean disponible) {
		this.idHotel = idHotel;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.pais = pais;
		this.categoria = categoria;
		this.precio = precio;
		this.disponible = disponible;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idHotel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelDTO other = (HotelDTO) obj;
		return Objects.equals(idHotel, other.idHotel);
	}

	@Override
	public String toString() {
		return "HotelDTO [idHotel=" + idHotel + ", nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio
				+ ", disponible=" + disponible + "]";
	}
	
	
}
