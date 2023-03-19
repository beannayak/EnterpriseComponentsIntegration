package com.rasal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "set")
public class OrderClient {
    private String orderId;
    private String customerId;
    private String supplierId;
    private String firstName;
    private String lastName;
    private Integer items;
    private Integer price;
    private float weight;
    private Boolean automatedEmail;
}
