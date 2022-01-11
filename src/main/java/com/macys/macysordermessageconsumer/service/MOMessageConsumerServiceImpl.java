package com.macys.macysordermessageconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

        MessageReceiver receiver =
                (PubsubMessage message, AckReplyConsumer consumer) -> message.getAttributesMap().forEach((key, value) -> {
                    if (value.equalsIgnoreCase("xml")) {
                        FulfillmentOrder fulfillmentOrder = saveXmlMessageInDB(new String((byte[]) Objects.requireNonNull(xmlAmqpTemplate.receiveAndConvert())));
                        if (fulfillmentOrder != null) {
                            fulfillmentOrderList.add(fulfillmentOrder);
                            consumer.ack();
                        }
                    }
                });

        Subscriber subscriber = null;
        ProjectSubscriptionName subscriptionName =
                ProjectSubscriptionName.of(projectId, GCPConstants.SUBSCRIPTION_ORDER_MSG);
        try {
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
            // Start the subscriber.
            subscriber.startAsync().awaitRunning();
            // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);
        } catch (TimeoutException timeoutException) {
            subscriber.stopAsync();
        }
        return fulfillmentOrderList;
    }


    private List<OrderMessageJson> getJsonMessagesFromGCP() {
        List<OrderMessageJson> orderMessageJsonList = new ArrayList<>();

        MessageReceiver receiver =
                (PubsubMessage message, AckReplyConsumer consumer) -> message.getAttributesMap().forEach((key, value) -> {
                    if (value.equalsIgnoreCase("json")) {
                        OrderMessageJson orderMessageJson = saveJsonMessageInDB(new String((byte[]) Objects.requireNonNull(jsonAmqpTemplate.receiveAndConvert())));
                        if (orderMessageJson != null) {
                            orderMessageJsonList.add(orderMessageJson);
                            consumer.ack();
                        }
                    }
                });

        Subscriber subscriber = null;
        ProjectSubscriptionName subscriptionName =
                ProjectSubscriptionName.of(projectId, GCPConstants.SUBSCRIPTION_ORDER_MSG);
        try {
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
            // Start the subscriber.
            subscriber.startAsync().awaitRunning();
            // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);
        } catch (TimeoutException timeoutException) {
            subscriber.stopAsync();
        }
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
