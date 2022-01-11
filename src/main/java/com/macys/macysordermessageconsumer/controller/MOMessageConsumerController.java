package com.macys.macysordermessageconsumer.controller;

import com.macys.macysordermessageconsumer.dto.json.OrderMessageJson;
import com.macys.macysordermessageconsumer.dto.xml.FulfillmentOrder;
import com.macys.macysordermessageconsumer.service.MOMessageConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/macy/consumer")
public class MOMessageConsumerController {

    @Autowired
    MOMessageConsumerService messageService;

    @GetMapping(value = "/xml",
            produces = {MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<List<FulfillmentOrder>> getXmlMessage(@RequestHeader("x-messaging-queue") String queue) {
        return messageService.getXmlMessage(queue);
    }

    @GetMapping(value = "/json",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<OrderMessageJson>> getJsonMessage(@RequestHeader("x-messaging-queue") String queue) {
        return messageService.getJsonMessage(queue);
    }
}
