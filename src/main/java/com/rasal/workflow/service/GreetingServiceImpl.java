package com.rasal.workflow.service;

import com.rasal.workflow.grpc.GreetingServiceGrpc;
import com.rasal.workflow.grpc.OrderRequest;
import com.rasal.workflow.grpc.OrderResponse;
import com.rasal.demo.Order;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Autowired
    private ProducerService producerService;

    @Override
    public void kafkaHello(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        Order order = Order.newBuilder()
                .setOrderId(request.getOrderId())
                .setCustomerId(request.getCustomerId())
                .setSupplierId(request.getSupplierId())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setItems(request.getItems())
                .setPrice(request.getPrice())
                .setWeight(request.getWeight())
                .setAutomatedEmail(request.getAutomatedEmail())
                .build();

        boolean response = producerService.sendToAvroTopic(order);
        responseObserver.onNext(OrderResponse.newBuilder().setSuccessful(response).build());
        responseObserver.onCompleted();
    }
}
