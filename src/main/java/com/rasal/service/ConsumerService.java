package com.rasal.service;

import com.rasal.demo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

    private static final String AVRO_TOPIC_NAME = "mytopic";

    @KafkaListener(topics = AVRO_TOPIC_NAME, groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, GenericRecord> record) {
        Order order = (Order) SpecificData.get().deepCopy(record.value().getSchema(), record.value());
        log.info("Consumed message -> {}", order);
    }
}
