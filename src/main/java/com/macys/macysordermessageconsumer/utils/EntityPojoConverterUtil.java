package com.macys.macysordermessageconsumer.utils;

import com.macys.macysordermessageconsumer.dto.json.OrderMessageJson;
import com.macys.macysordermessageconsumer.dto.xml.FulfillmentOrder;
import com.macys.macysordermessageconsumer.entity.json.OrderMessageJsonEntity;
import com.macys.macysordermessageconsumer.entity.xml.FulfillmentOrderEntity;
import com.macys.macysordermessageconsumer.entity.xml.Source;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class EntityPojoConverterUtil {

    public static OrderMessageJson jsonEntityToPojo(ModelMapper modelMapper, OrderMessageJsonEntity orderMessageJsonEntity) {
        return modelMapper.map(orderMessageJsonEntity, OrderMessageJson.class);
    }

    public static OrderMessageJsonEntity jsonPojoToEntity(ModelMapper modelMapper, OrderMessageJson orderMessageJson) {
        return modelMapper.map(orderMessageJson, OrderMessageJsonEntity.class);
    }

    public static FulfillmentOrderEntity xmlPojoToEntity(ModelMapper modelMapper, FulfillmentOrder fulfillmentOrder) {
        return modelMapper.map(fulfillmentOrder, FulfillmentOrderEntity.class);
    }

    public static FulfillmentOrder xmlEntityToPojo(ModelMapper modelMapper, FulfillmentOrderEntity fulfillmentOrderEntity) {
        return modelMapper.map(fulfillmentOrderEntity, FulfillmentOrder.class);
    }
}
