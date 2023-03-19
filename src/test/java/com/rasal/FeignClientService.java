package com.rasal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;

@FeignClient (value="feignClientService", url="http://localhost:8080")
public interface FeignClientService {

    @RequestMapping(method = RequestMethod.POST, value = "/kafka/send-avro", consumes = MediaType.APPLICATION_JSON)
    public boolean sendAvro(@RequestHeader("x-requested-id") String requester, OrderClient order);
}
