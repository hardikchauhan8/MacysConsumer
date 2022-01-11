package com.macys.macysordermessageconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.macys.macysordermessageconsumer.dto.json.OrderMessageJson;
import com.macys.macysordermessageconsumer.dto.xml.FulfillmentOrder;
import com.macys.macysordermessageconsumer.entity.json.OrderMessageJsonEntity;
import com.macys.macysordermessageconsumer.entity.xml.FulfillmentOrderEntity;
import com.macys.macysordermessageconsumer.exception.ErrorSavingDataToDatabaseException;
import com.macys.macysordermessageconsumer.repository.JsonMessageRepository;
import com.macys.macysordermessageconsumer.repository.XmlMessageRepository;
import com.macys.macysordermessageconsumer.utils.AMQPConstants;
import com.macys.macysordermessageconsumer.utils.EntityPojoConverterUtil;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ResponseEntity<List<FulfillmentOrder>> getXmlMessage() {
        List<FulfillmentOrder> fulfillmentOrderList = new ArrayList<>();
        Properties properties = rabbitAdmin.getQueueProperties(AMQPConstants.XML_QUEUE);
        int reqCount = (Integer) (properties != null ? properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT) : 0);
        for (int i = 0; i < reqCount; i++) {
            try {
                FulfillmentOrder fulfillmentOrder = new XmlMapper().readValue(new String((byte[]) xmlAmqpTemplate.receiveAndConvert()), FulfillmentOrder.class);
                FulfillmentOrderEntity entity = EntityPojoConverterUtil.xmlPojoToEntity(modelMapper, fulfillmentOrder);
                FulfillmentOrderEntity affectedEntity = null;
                try {
                    affectedEntity = xmlMessageRepository.save(entity);
                    fulfillmentOrderList.add(fulfillmentOrder);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    if (affectedEntity == null) {
                        throw new ErrorSavingDataToDatabaseException();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        return new ResponseEntity<>(fulfillmentOrderList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderMessageJson>> getJsonMessage() {
        List<OrderMessageJson> orderMessageJsonList = new ArrayList<>();
        Properties properties = rabbitAdmin.getQueueProperties(AMQPConstants.JSON_QUEUE);
        int reqCount = (Integer) (properties != null ? properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT) : 0);
        for (int i = 0; i < reqCount; i++) {
            try {
                OrderMessageJson orderMessageJson = new ObjectMapper().readValue(new String((byte[]) jsonAmqpTemplate.receiveAndConvert()), OrderMessageJson.class);
                OrderMessageJsonEntity entity = EntityPojoConverterUtil.jsonPojoToEntity(modelMapper, orderMessageJson);
                OrderMessageJsonEntity affectedEntity = null;
                try {
                    affectedEntity = jsonMessageRepository.save(entity);
                    orderMessageJsonList.add(orderMessageJson);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    if (affectedEntity == null) {
                        throw new ErrorSavingDataToDatabaseException();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        return new ResponseEntity<>(orderMessageJsonList, HttpStatus.OK);
    }
//    @RabbitListener(queues = {"${queue.name}"})
//    public <T> void receive(T data) {
//        OrderMessageJson orderMessageJson = null;
//        FulfillmentOrder fulfillmentOrder = null;
//        try {
//            orderMessageJson = new ObjectMapper().readValue(new String(((Message) data).getBody()), OrderMessageJson.class);
//        } catch (JsonProcessingException e) {
//            try {
//                fulfillmentOrder = new XmlMapper().readValue(new String(((Message) data).getBody()), FulfillmentOrder.class);
//            } catch (Exception ex) {
//                e.printStackTrace();
//                ex.printStackTrace();
//            }
//        } finally {
//            if (orderMessageJson != null) {
//                OrderMessageJsonEntity entity = EntityPojoConverterUtil.jsonPojoToEntity(modelMapper, orderMessageJson);
//                OrderMessageJsonEntity affectedEntity = null;
//                try {
//                    affectedEntity = jsonMessageRepository.save(entity);
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (affectedEntity == null) {
//                        throw new ErrorSavingDataToDatabaseException();
//                    }
//                }
//            } else if (fulfillmentOrder != null) {
//                FulfillmentOrderEntity entity = EntityPojoConverterUtil.xmlPojoToEntity(modelMapper, fulfillmentOrder);
//                FulfillmentOrderEntity affectedEntity = null;
//                try {
//                    affectedEntity = xmlMessageRepository.save(entity);
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (affectedEntity == null) {
//                        throw new ErrorSavingDataToDatabaseException();
//                    }
//                }
//            } else {
////                throw new ErrorParsingDataException();
//            }
//        }
//    }

}
