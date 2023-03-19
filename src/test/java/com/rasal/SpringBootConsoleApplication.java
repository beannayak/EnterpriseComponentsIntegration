package com.rasal;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.UUID;

public class SpringBootConsoleApplication {

    public static void main(String[] args) {
        FeignClientService service = Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClientService.class, "http://localhost:8080");


        OrderClient order = OrderClient.builder()
                .setOrderId("OrderId:2")
                .setCustomerId("CustomerId:2")
                .setSupplierId("SupplierId:2")
                .setFirstName("FirstName:2")
                .setLastName("LastName:2")
                .setItems(2)
                .setPrice(2)
                .setWeight(2.2f)
                .setAutomatedEmail(true)
                .build();

        service.sendAvro(UUID.randomUUID().toString(), order);
    }
}
