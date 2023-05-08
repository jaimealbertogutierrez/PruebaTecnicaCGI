package com.demo.prueba.cgi.democolaskafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import com.demo.prueba.cgi.dto.PayloadConsultaDTO;


@SpringBootApplication
@ComponentScan({"com.demo.prueba.cgi.controller"})
@EnableMongoRepositories("com.demo.prueba.cgi.mongodb.dal")
public class DemoColasKafkaApplication {

	private final Logger loggerApp = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(DemoColasKafkaApplication.class, args);
	}
	 
    @KafkaListener(topics = "hotelAvailabilitySearches", groupId = "manejador_mensajes",
	containerFactory = "kafkaListenerContainerFactory")
	public void listen(PayloadConsultaDTO datosPayload) {
    		loggerApp.info ("***************************** Procesando mensaje");
    		loggerApp.info("Mensaje capturado del topico: " + datosPayload);
	}
}
