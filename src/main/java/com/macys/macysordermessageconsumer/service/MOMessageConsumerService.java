package com.macys.macysordermessageconsumer.service;


import com.macys.macysordermessageconsumer.dto.json.OrderMessageJson;
import com.macys.macysordermessageconsumer.dto.xml.FulfillmentOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MOMessageConsumerService {
    ResponseEntity<List<FulfillmentOrder>> getXmlMessage(String queue);

    ResponseEntity<List<OrderMessageJson>> getJsonMessage(String queue);
}
