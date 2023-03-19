package com.rasal.service;

import com.rasal.repository.OrderRepository;
import com.rasal.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderById(long id) {
        return orderRepository.getReferenceById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
