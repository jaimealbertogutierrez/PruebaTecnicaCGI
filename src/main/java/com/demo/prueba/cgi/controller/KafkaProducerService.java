package com.demo.prueba.cgi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.prueba.cgi.dto.PayloadConsultaDTO;

@Service
public class KafkaProducerService {
    private final Logger loggerProducer = LoggerFactory.getLogger(KafkaProducerService.class);
    
    @Value("${spring.kafka.topic}")
    private String kafkaTopic;
    
    @Autowired
    private KafkaTemplate<String, PayloadConsultaDTO> kafkaTemplate;


    public void send(PayloadConsultaDTO payloadConsulta) {
    	loggerProducer.info("Sending User Json Serializer : {}", payloadConsulta);
        loggerProducer.info ("Topico de envio: " + kafkaTopic);
        kafkaTemplate.send(kafkaTopic, payloadConsulta);
    }
}