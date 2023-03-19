package com.rasal.controller;

import com.rasal.service.ProducerService;
import com.rasal.demo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/send-text", method = RequestMethod.POST)
    public ResponseEntity<Boolean> sendMessage(@RequestBody String msg) {
        boolean successful = producerService.sendToStringStringTopic(msg);
        return successful ? ResponseEntity.ok(true) : ResponseEntity.internalServerError().body(false);
    }

    @RequestMapping(value = "/send-avro", method = RequestMethod.POST)
    public ResponseEntity<Boolean> sendAvro(HttpServletRequest servletRequest, @RequestBody Order order) {
        log.info("got requested-id header: {}", servletRequest.getHeader("x-requested-id"));
        boolean successful = producerService.sendToAvroTopic(order);
        return successful ? ResponseEntity.ok(true) : ResponseEntity.internalServerError().body(false);
    }
}
