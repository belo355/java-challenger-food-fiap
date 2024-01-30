package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.application.domain.entities.Order;
import com.fiap.challenger.food.common.StatusOrderEnum;
import com.fiap.challenger.food.common.form.OrderFormDto;
import com.fiap.challenger.food.infraestruture.gateway.OrderGateway;
import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MakeCreateNewOrderUseCase {

    private final OrderGateway gateway;
    private static final Logger logger = LoggerFactory.getLogger(MakeCreateNewOrderUseCase.class);

    @Autowired
    public MakeCreateNewOrderUseCase(OrderGateway orderGateway) {
        this.gateway = orderGateway;
    }
    public ResponseEntity<Long> create(OrderFormDto orderFormDto) {
        Order order = new Order(orderFormDto);
        if (order.getProductos().isEmpty()) {
            logger.info("Pedido nao pode ser criado sem produtos selecionados");
            return ResponseEntity.noContent().build();
        } else {
            OrderRepositoryDb orderRepositoryDb = new OrderRepositoryDb(order);
            orderRepositoryDb.setStatusOrderEnum(StatusOrderEnum.RECEBIDO);
            return gateway.create(orderRepositoryDb);
        }
    }
}
