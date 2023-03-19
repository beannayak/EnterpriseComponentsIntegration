package com.rasal.workflow;

import com.rasal.workflow.grpc.GreetingServiceGrpc;
import com.rasal.workflow.grpc.OrderRequest;
import com.rasal.workflow.grpc.OrderResponse;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.ClientAuth;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLException;
import java.io.File;

public class GrpcTest {

    @Test
    public void testGrpcCall() {
        ManagedChannel channel;
        try {
            channel = NettyChannelBuilder.forAddress("localhost", 9090)
                    .useTransportSecurity()
                    .negotiationType(NegotiationType.TLS)
                    .sslContext(GrpcSslContexts.forClient()
                            .keyManager(new File("/home/binayak/.keys/client-cert.pem"), new File("/home/binayak/.keys/client-key.pem"))
                            .trustManager(new File("/home/binayak/.keys/ca-cert.pem"))
                            .clientAuth(ClientAuth.REQUIRE)
                            .build())
                    .build();
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
        GreetingServiceGrpc.GreetingServiceBlockingStub blockingStub = GreetingServiceGrpc.newBlockingStub(channel);

        OrderRequest orderRequest = OrderRequest.newBuilder()
                .setOrderId("OrderId:1")
                .setCustomerId("CustomerId:1")
                .setSupplierId("SupplierId:1")
                .setFirstName("FirstName:1")
                .setLastName("LastName:1")
                .setItems(1)
                .setPrice(1.1f)
                .setWeight(1.1f)
                .setAutomatedEmail(true)
                .build();

        OrderResponse orderResponse = blockingStub.kafkaHello(orderRequest);
        System.out.println("Response: " + orderResponse.getSuccessful());
    }
}
