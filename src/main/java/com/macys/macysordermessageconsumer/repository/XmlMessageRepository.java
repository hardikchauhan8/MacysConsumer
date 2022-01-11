package com.macys.macysordermessageconsumer.repository;

import com.macys.macysordermessageconsumer.entity.xml.FulfillmentOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XmlMessageRepository extends JpaRepository<FulfillmentOrderEntity, Integer> {
}