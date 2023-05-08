package com.demo.prueba.cgi.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.prueba.cgi.dto.*;
import com.demo.prueba.cgi.mongodb.dal.PayloadConsultaRepository;
import com.demo.prueba.cgi.mongodb.entities.PayloadConsultaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class DemoController {

	private final Logger loggerApp = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ManejadorDatosMongo gestorMongoDB;
	
	@Autowired
	private PayloadConsultaRepository repository;

    @Autowired
    private KafkaProducerService kafkaProducer;
    

    
	//Métodos del servicio para la implementación de las capacidades del microservicio
	@RequestMapping(value = "/search", method= RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<BusquedaDTO> search (@RequestBody PayloadConsultaDTO datosEntrada) {
		
		String searchId = "";
		boolean yaEsta = false;
		
		yaEsta = repository.existsById(datosEntrada.getHotelId());
		loggerApp.info ("El registro ya está: " + yaEsta);
		ObjectId idObjetoNuevo = null;
		
		if (yaEsta == true)
		{
			loggerApp.info ("Ya se insertaron esos datos");
			Optional<PayloadConsultaEntity> reg = null;
			reg = gestorMongoDB.obtenerRegistroEntidadPorId(datosEntrada.getHotelId());
			loggerApp.info ("Registro existente: " + reg);
			PayloadConsultaEntity original = reg.get();
			loggerApp.info ("Id del original: " + original.getHotelId());
			idObjetoNuevo = ObjectId.get();
			searchId = gestorMongoDB.insertarRegistro(datosEntrada,idObjetoNuevo);
		}
		else {
			loggerApp.info ("Se inserta por primera vez");
			searchId = gestorMongoDB.insertarRegistro(datosEntrada,null);
		}
		
		loggerApp.info ("Enviando al topico kafka");
		kafkaProducer.send(datosEntrada);
		loggerApp.info ("Id obtenido: " + searchId);
		
		return new ResponseEntity<BusquedaDTO>(new BusquedaDTO(searchId),HttpStatus.OK);
		
	}
	//*******************************************************************
	//*******************************************************************
	//*******************************************************************
	@RequestMapping(value = "/count", method= RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> count (@RequestBody BusquedaDTO parametroConsulta) {
	//public String count (@RequestBody Busqueda parametroConsulta) {
		int contadorRegistros = 0;
		int i = 0;
		String valorParametro = parametroConsulta.getSearchId();
		String idHotel = "";
		int totalRegistrosEncontrados = 0;
		Optional<PayloadConsultaEntity> entidad = null;
		PayloadConsultaEntity original = null;
		List<PayloadConsultaEntity> registros = null;
		ResponseEntity<?> responseMetodo = null;
		
		try {
			loggerApp.info ("Parámetro consultado: " + valorParametro);
			entidad = repository.findById(parametroConsulta.getSearchId());
			original = entidad.get();
			idHotel = original.getHotelId();
			
			registros = repository.findAll();
			
			for (i = 0;  i < registros.size();  i++) {
				PayloadConsultaEntity regIn = (PayloadConsultaEntity)registros.get(i);
				
				if (regIn.getHotelId().equals(idHotel)) {
					contadorRegistros++;
					loggerApp.info ("Se trajo el registro: " + regIn.getHotelId() + " con search id: " + regIn.getSearchId());
					loggerApp.info(regIn.toJson());
				}
			}
			
			totalRegistrosEncontrados = contadorRegistros;
			loggerApp.info ("Registros en la colección: " + totalRegistrosEncontrados);
			
			PayloadConsultaDTO registroPantalla = new PayloadConsultaDTO();
			
			ResultadoBusquedaDTO retornoServicio = new ResultadoBusquedaDTO();
			retornoServicio.setSearchId(parametroConsulta.getSearchId());
			
			registroPantalla.setAges(original.getAges());
			registroPantalla.setCheckIn(original.getCheckIn());
			registroPantalla.setCheckOut(original.getCheckOut());
			registroPantalla.setHotelId(original.getHotelId());
			retornoServicio.setSearch(registroPantalla);
			retornoServicio.setCount(totalRegistrosEncontrados);
			
			responseMetodo = new ResponseEntity<ResultadoBusquedaDTO>(retornoServicio,HttpStatus.OK);
		}
		
		catch (HttpMessageNotReadableException errorGeneral) {
			errorGeneral.printStackTrace();
			responseMetodo = ResponseEntity
		            .status(HttpStatus.FORBIDDEN)
		            .body("Cuerpo incorrecto de la petición");
			
			return (responseMetodo);
		}
		
		
		
		return (responseMetodo);
	}
	//*******************************************************************
	//*******************************************************************
	//*******************************************************************
	
}