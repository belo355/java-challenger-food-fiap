package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.common.StatusOrderEnum;
import com.fiap.challenger.food.infraestruture.gateway.OrderGateway;
import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeListAllOrdersByStatusUseCase {

    private final OrderGateway gateway;
    private static final Logger logger = LoggerFactory.getLogger(MakeListAllOrdersByStatusUseCase.class);

    @Autowired
    public MakeListAllOrdersByStatusUseCase(OrderGateway orderGateway) {
        this.gateway = orderGateway;
    }

    public ResponseEntity findByStatus(String status) {
        StatusOrderEnum statusOrderEnum = StatusOrderEnum.valueOf(status.toUpperCase());
        List<OrderRepositoryDb> orders = gateway.findByStatus(statusOrderEnum);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            logger.info("Não ha pedidos neste status: " + status);
            return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
        }
    }
}
