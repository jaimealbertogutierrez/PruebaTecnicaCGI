package com.demo.prueba.cgi.controller;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.prueba.cgi.dto.PayloadConsultaDTO;
import com.demo.prueba.cgi.mongodb.dal.PayloadConsultaRepository;
import com.demo.prueba.cgi.mongodb.entities.PayloadConsultaEntity;


@Service
public class ManejadorDatosMongo {

	@Autowired
	private PayloadConsultaRepository payloadRepository;

	
	public ManejadorDatosMongo() {
		// TODO Auto-generated constructor stub
	}
  
    public Optional<PayloadConsultaEntity> obtenerRegistroEntidadPorId (String idRegistro) {
    	Optional<PayloadConsultaEntity> registroBDMongoConsulta = null;
    	registroBDMongoConsulta = payloadRepository.findById(idRegistro);
    	return (registroBDMongoConsulta);
    }
	 
	public String insertarRegistro(PayloadConsultaDTO registro, ObjectId idConsulta) {
		 PayloadConsultaEntity registroBDMongoConsulta = null;
		 PayloadConsultaEntity registroBDMongo = new PayloadConsultaEntity();
		 ObjectId idRegistro = null;
		 
		 if (idConsulta == null) {
			 System.out.println ("Generando uno nuevo");
			 idRegistro = ObjectId.get();
		 }
		 else {
			 System.out.println ("Usando el enviado por parametro");
			 idRegistro = idConsulta;
		 }
			 
		 System.out.println ("Object id generado: " + idRegistro.toHexString());
		 
		 registroBDMongo.setSearchId(idRegistro);
		 registroBDMongo.setCheckIn(registro.getCheckIn());
		 registroBDMongo.setCheckOut(registro.getCheckOut());
		 registroBDMongo.setHotelId(registro.getHotelId());
		 registroBDMongo.setAges(registro.getAges());
		 String idBusqueda = "";
		 
		 try
		 {
			 System.out.println ("-----------------------------------------");
			 System.out.println ("Insertando registro en la bd mongo: " + registroBDMongo.getHotelId());
			 registroBDMongoConsulta = payloadRepository.save(registroBDMongo);
			 
			 System.out.println ("-----------------------------------------");
			 System.out.println (registroBDMongo.toJson());
			 System.out.println ("-----------------------------------------");
			 
			 idBusqueda = registroBDMongoConsulta.getSearchId().toHexString();
			 System.out.println ("Id de la entidad recien insertada: " + idBusqueda);
			 System.out.println ("----------------------------------------->");
			 //conectarBDMongo();
		 }
		 
		 catch (Exception error)
		 {
			 System.out.println ("Error específico: " + error.getLocalizedMessage());
			 error.printStackTrace();
		 }
		 
		 return (idBusqueda);
	 }

}
