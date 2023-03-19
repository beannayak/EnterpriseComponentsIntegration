package com.rasal.workflow.configurations;

import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.ClientAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Configuration
public class GrpcServerConfigurer {

    @Bean
    public net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer serverConfigurer() {
        return serverBuilder -> {
            if (serverBuilder instanceof NettyServerBuilder nettyServerBuilder) {
                try {
                    nettyServerBuilder.sslContext(GrpcSslContexts
                            .forServer(new FileInputStream("/home/binayak/.keys/server-cert.pem"),
                                       new FileInputStream("/home/binayak/.keys/server-key.pem"))
                            .trustManager(new FileInputStream("/home/binayak/.keys/ca-cert.pem"))
                            .clientAuth(ClientAuth.REQUIRE)
                            .build());
                } catch (SSLException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
