package com.macys.macysordermessageconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.pubsub.v1.PubsubMessage;
import com.macys.macysordermessageconsumer.dto.json.OrderMessageJson;
import com.macys.macysordermessageconsumer.dto.xml.FulfillmentOrder;
import com.macys.macysordermessageconsumer.entity.json.OrderMessageJsonEntity;
import com.macys.macysordermessageconsumer.entity.xml.FulfillmentOrderEntity;
import com.macys.macysordermessageconsumer.exception.ErrorSavingDataToDatabaseException;
import com.macys.macysordermessageconsumer.repository.JsonMessageRepository;
import com.macys.macysordermessageconsumer.repository.XmlMessageRepository;
import com.macys.macysordermessageconsumer.utils.AMQPConstants;
import com.macys.macysordermessageconsumer.utils.EntityPojoConverterUtil;
import com.macys.macysordermessageconsumer.utils.GCPConstants;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
public class MOMessageConsumerServiceImpl implements MOMessageConsumerService {

    @Autowired
    XmlMessageRepository xmlMessageRepository;

    @Autowired
    JsonMessageRepository jsonMessageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Autowired
    AmqpTemplate xmlAmqpTemplate;

    @Autowired
    AmqpTemplate jsonAmqpTemplate;

    @Autowired
    PubSubTemplate pubSubTemplate;

    @Value("${spring.cloud.gcp.project-id}")
    String projectId;

    @Override
    public ResponseEntity<List<FulfillmentOrder>> getXmlMessage(String queue) {
        List<FulfillmentOrder> fulfillmentOrderList;
        if (queue.equalsIgnoreCase("gcp")) {
            fulfillmentOrderList = getXMLMessagesFromGCP();
        } else {
            fulfillmentOrderList = getXMLMessagesFromRabbitMQ();
        }
        return new ResponseEntity<>(fulfillmentOrderList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderMessageJson>> getJsonMessage(String queue) {
        List<OrderMessageJson> orderMessageJsonList;
        if (queue.equalsIgnoreCase("gcp")) {
            orderMessageJsonList = getJsonMessagesFromGCP();
        } else {
            orderMessageJsonList = getJsonMessagesFromRabbitMQ();
        }
        return new ResponseEntity<>(orderMessageJsonList, HttpStatus.OK);
    }

    private List<FulfillmentOrder> getXMLMessagesFromGCP() {
        List<FulfillmentOrder> fulfillmentOrderList = new ArrayList<>();

        PubsubMessage message;
        while ((message = pubSubTemplate.pullNext(GCPConstants.SUBSCRIPTION_XML_ORDER)) != null) {
            String xmlMessage = message.getData().toStringUtf8();
            FulfillmentOrder fulfillmentOrder = saveXmlMessageInDB(xmlMessage);
            if (fulfillmentOrder != null) {
                fulfillmentOrderList.add(fulfillmentOrder);
            }
        }

        System.out.println("No of xml messages read : " + fulfillmentOrderList.size());
        return fulfillmentOrderList;
    }


    private List<OrderMessageJson> getJsonMessagesFromGCP() {
        List<OrderMessageJson> orderMessageJsonList = new ArrayList<>();

        PubsubMessage message;
        while ((message = pubSubTemplate.pullNext(GCPConstants.SUBSCRIPTION_JSON_ORDER)) != null) {
            String jsonMessage = message.getData().toStringUtf8();
            OrderMessageJson orderMessageJson = saveJsonMessageInDB(jsonMessage);
            if (orderMessageJson != null) {
                orderMessageJsonList.add(orderMessageJson);
            }
        }

        System.out.println("No of json messages read : " + orderMessageJsonList.size());
        return orderMessageJsonList;
    }

    private List<FulfillmentOrder> getXMLMessagesFromRabbitMQ() {
        List<FulfillmentOrder> fulfillmentOrderList = new ArrayList<>();
        Properties properties = rabbitAdmin.getQueueProperties(AMQPConstants.XML_QUEUE);
        int reqCount = (Integer) (properties != null ? properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT) : 0);
        for (int i = 0; i < reqCount; i++) {
            FulfillmentOrder fulfillmentOrder = saveXmlMessageInDB(new String((byte[]) Objects.requireNonNull(xmlAmqpTemplate.receiveAndConvert())));
            if (fulfillmentOrder != null) {
                fulfillmentOrderList.add(fulfillmentOrder);
            }
        }
        return fulfillmentOrderList;
    }

    private List<OrderMessageJson> getJsonMessagesFromRabbitMQ() {
        List<OrderMessageJson> orderMessageJsonList = new ArrayList<>();
        Properties properties = rabbitAdmin.getQueueProperties(AMQPConstants.JSON_QUEUE);
        int reqCount = (Integer) (properties != null ? properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT) : 0);
        for (int i = 0; i < reqCount; i++) {
            OrderMessageJson orderMessageJson = saveJsonMessageInDB(new String((byte[]) Objects.requireNonNull(jsonAmqpTemplate.receiveAndConvert())));
            if (orderMessageJson != null) {
                orderMessageJsonList.add(orderMessageJson);
            }
        }
        return orderMessageJsonList;
    }

    private FulfillmentOrder saveXmlMessageInDB(String xmlMessage) {
        try {
            FulfillmentOrder fulfillmentOrder = new XmlMapper().readValue(xmlMessage, FulfillmentOrder.class);
            FulfillmentOrderEntity entity = EntityPojoConverterUtil.xmlPojoToEntity(modelMapper, fulfillmentOrder);
            FulfillmentOrderEntity affectedEntity = null;
            try {
                affectedEntity = xmlMessageRepository.save(entity);
                return fulfillmentOrder;
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (affectedEntity == null) {
                    throw new ErrorSavingDataToDatabaseException();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private OrderMessageJson saveJsonMessageInDB(String jsonMessage) {
        try {
            OrderMessageJson orderMessageJson = new ObjectMapper().readValue(jsonMessage, OrderMessageJson.class);
            OrderMessageJsonEntity entity = EntityPojoConverterUtil.jsonPojoToEntity(modelMapper, orderMessageJson);
            OrderMessageJsonEntity affectedEntity = null;
            try {
                affectedEntity = jsonMessageRepository.save(entity);
                return orderMessageJson;
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (affectedEntity == null) {
                    throw new ErrorSavingDataToDatabaseException();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
