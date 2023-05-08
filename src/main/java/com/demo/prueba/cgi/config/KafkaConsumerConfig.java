package com.demo.prueba.cgi.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.demo.prueba.cgi.dto.PayloadConsultaDTO;



@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	private final Logger loggerApp = LoggerFactory.getLogger(getClass());

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${spring.kafka.topid_id}")
    private String topic_Id;
    

    @Bean
    public ConsumerFactory<String, PayloadConsultaDTO> payloadFactory() {
        Map<String, Object> props = new HashMap<>();
        
        loggerApp.info ("Servidor kafka: " + bootstrapServers);
        loggerApp.info ("TOPIC ID: " + topic_Id);
        
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, topic_Id);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(PayloadConsultaDTO.class));
    }

	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, PayloadConsultaDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PayloadConsultaDTO>
                factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(payloadFactory());
        return factory;
    }
    
    @Bean
    public KafkaTemplate<String, PayloadConsultaDTO> kafkaTemplate(
            ProducerFactory<String, PayloadConsultaDTO> payloadFactory) {

        KafkaTemplate<String, PayloadConsultaDTO> kafkaTemplate = new KafkaTemplate<>(payloadFactory);
        return kafkaTemplate;
    }
}