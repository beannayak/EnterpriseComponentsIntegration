package com.rasal.workflow.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "OrderTable")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String customerId;
    private String supplierId;
    private String firstName;
    private String lastName;
    private Integer items;
    private Double price;
    private Double weight;
    private Boolean automatedEmail;
}
