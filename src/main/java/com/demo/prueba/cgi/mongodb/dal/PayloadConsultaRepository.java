package com.demo.prueba.cgi.mongodb.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.demo.prueba.cgi.mongodb.entities.PayloadConsultaEntity;

@Repository
public interface PayloadConsultaRepository extends MongoRepository<PayloadConsultaEntity, String> {
}

