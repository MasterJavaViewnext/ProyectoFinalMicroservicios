package com.curso.reserva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.reserva.model.Reserva;
import com.curso.reserva.service.ReservaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin("*")   //politica de CORS(Cross-Origin Resource Sharing) que implementan los navegadores
@RestController
@RequestMapping("reservas")
@Api(tags = "ReservaRestController", description = "Operaciones de gestión de reservas")
public class ReservaRestController {

    @Autowired
    private ReservaService service;

    /**
     * Obtiene todas las reservas.
     * 
     * @return una lista de todas las reservas
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener todas las reservas", notes = "Devuelve una lista de todas las reservas")
    public List<Reserva> findAll() {
        return service.findAll();
    }

    /**
     * Obtiene una reserva por su ID.
     * 
     * @param id el ID de la reserva
     * @return la reserva con el ID especificado o null si no se encuentra
     */
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener una reserva por ID", notes = "Devuelve una reserva específica según su ID")
    public Reserva findById(@ApiParam(value = "ID de la reserva", required = true) @PathVariable long id) {
        return service.findById(id);
    }

    /**
     * Inserta una nueva reserva.
     * 
     * @param reserva el objeto Reserva a insertar
     * @return ResponseEntity con el código HTTP 201 si se creó la reserva, o 409 si no hay suficientes plazas o el hotel no está disponible
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Insertar una nueva reserva", notes = "Crea una nueva reserva y devuelve el estado de la operación")
    public ResponseEntity<String> insert(@ApiParam(value = "Objeto Reserva a insertar", required = true) @RequestBody Reserva reserva) {
        ResponseEntity<String> ret;
        if (service.insert(reserva)) {
            ret = ResponseEntity.status(HttpStatus.CREATED).body("HTTP STATUS 201: Reserva creada");
        } else {
            ret = ResponseEntity.status(HttpStatus.CONFLICT).body("HTTP STATUS 409: Hotel no disponible o vuelo sin plazas disponibles suficientes");
        }
        return ret;
    }

    /**
     * Elimina una reserva por su ID.
     * 
     * @param id el ID de la reserva a eliminar
     */
    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Eliminar una reserva por ID", notes = "Elimina una reserva específica según su ID")
    public void deleteById(@ApiParam(value = "ID de la reserva a eliminar", required = true) @PathVariable long id) {
        service.deleteById(id);
    }

    /**
     * Obtiene reservas por el nombre del hotel.
     * 
     * @param nombre el nombre del hotel
     * @return una lista de reservas en el hotel especificado
     */
    @GetMapping(value = "hotel/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener reservas por nombre del hotel", notes = "Devuelve una lista de reservas en un hotel específico según su nombre")
    public List<Reserva> findByHotelNombre(@ApiParam(value = "Nombre del hotel", required = true) @PathVariable("nombre") String nombre) {
        return service.findByHotelNombre(nombre);
    }
}
