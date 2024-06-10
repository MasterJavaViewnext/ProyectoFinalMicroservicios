package com.curso.reserva;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.reserva.model.Reserva;
import com.curso.reserva.service.ReservaService;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReservaService service;
	
	@BeforeEach
	public void setup() {
		Reserva reserva = new Reserva(1l, 1l, 1l, 1l, 1);
		when(service.findById(1)).thenReturn(reserva);
		when(service.findAll()).thenReturn(Arrays.asList(reserva));
		when(service.findByHotelNombre("Hotel la paz")).thenReturn(Arrays.asList(reserva));
	}
	
	@Test
	public void InsertReservaTest() throws Exception {
		when(service.insert(any(Reserva.class))).thenReturn(true);
		mockMvc.perform(post("/reservas")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"idCliente\":\"1\", \"idHotel\":\"1\", \"idVuelo\":\"1\", \"numPersonas\":\"1\"}"))
			.andExpect(status().isCreated());
	}
	
	@Test
	public void deleteReservaByIdTest() throws Exception {
		doNothing().when(service).deleteById(1);
		mockMvc.perform(delete("/reservas/1"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void getListTodasLasReservasTest() throws Exception {
		mockMvc.perform(get("/reservas"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].idReserva", is(1)))
			.andExpect(jsonPath("$[0].idCliente", is(1)))
			.andExpect(jsonPath("$[0].idHotel", is(1)))
			.andExpect(jsonPath("$[0].idVuelo", is(1)))
			.andExpect(jsonPath("$[0].numPersonas", is(1)));
	}
	
	@Test
	public void getReservaByIdTest() throws Exception {
		mockMvc.perform(get("/reservas/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.idReserva", is(1)))
			.andExpect(jsonPath("$.idCliente", is(1)))
			.andExpect(jsonPath("$.idHotel", is(1)))
			.andExpect(jsonPath("$.idVuelo", is(1)))
			.andExpect(jsonPath("$.numPersonas", is(1)));
	}

}
