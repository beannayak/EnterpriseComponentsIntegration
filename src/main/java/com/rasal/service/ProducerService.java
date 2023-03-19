package com.rasal.service;

import com.rasal.demo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
public class ProducerService {
    private static final String STRING_TOPIC_NAME = "MyStringTopic";
    private static final String AVRO_TOPIC_NAME = "mytopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, GenericRecord> genericRecordKafkaTemplate;

    public boolean sendToStringStringTopic(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(STRING_TOPIC_NAME, message);
        try {
            SendResult<String, String> result = future.get();
            log.info("result: {}", result);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean sendToAvroTopic(Order order) {
        try {
            ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>(AVRO_TOPIC_NAME, order);
            genericRecordKafkaTemplate.send(producerRecord);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
