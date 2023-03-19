package com.rasal.workflow.controller;

import com.rasal.workflow.model.Order;
import com.rasal.workflow.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/save-order", method = RequestMethod.POST)
    public ResponseEntity<Boolean> saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/get-all-orders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllEntities() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
