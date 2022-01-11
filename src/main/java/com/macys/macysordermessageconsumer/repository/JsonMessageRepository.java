package com.macys.macysordermessageconsumer.repository;

import com.macys.macysordermessageconsumer.entity.json.OrderMessageJsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonMessageRepository extends JpaRepository<OrderMessageJsonEntity, Integer> {
}