package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.infraestruture.gateway.OrderGateway;
import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MakeListAllOrdersInDecreasingForStatusOrderUseCase {
    private final OrderGateway gateway;
    private static final Logger logger = LoggerFactory.getLogger(MakeListAllOrdersInDecreasingForStatusOrderUseCase.class);

    @Autowired
    public MakeListAllOrdersInDecreasingForStatusOrderUseCase(OrderGateway orderGateway) {
        this.gateway = orderGateway;
    }

    public ResponseEntity findAll() {
        List<OrderRepositoryDb> orders = gateway.findAll();
        orderListforDateReceive(orders);
        orderListforStatusOrder(orders);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    private void orderListforStatusOrder(List<OrderRepositoryDb> orders) {
        Collections.sort(orders, new Comparator<OrderRepositoryDb>() {
            @Override
            public int compare(OrderRepositoryDb o1, OrderRepositoryDb o2) {
                return Integer.compare(o2.getStatusOrderEnum().ordinal(), o1.getStatusOrderEnum().ordinal());
            }
        });
    }

    private void orderListforDateReceive(List<OrderRepositoryDb> orders) {
        Collections.sort(orders, new Comparator<OrderRepositoryDb>() {
            @Override
            public int compare(OrderRepositoryDb o1, OrderRepositoryDb o2) {
                return o1.getDateOrder().compareTo(o2.getDateOrder());
            }
        });
    }
}
