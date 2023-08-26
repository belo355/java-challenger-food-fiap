package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.application.domain.entities.Order;
import com.fiap.challenger.food.common.form.OrderFormDto;
import com.fiap.challenger.food.infraestruture.presentation.OrderPresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MakeCreateNewOrderUseCase {

    private final OrderPresentation orderPresentation;
    private static final Logger logger = LoggerFactory.getLogger(MakeCreateNewOrderUseCase.class);

    @Autowired
    public MakeCreateNewOrderUseCase(OrderPresentation orderPresentation) {
        this.orderPresentation = orderPresentation;
    }
    public ResponseEntity<Long> create(OrderFormDto orderFormDto) {
        Order order = new Order(orderFormDto);
        if (order.getProductos().isEmpty()) {
            logger.info("Pedido nao pode ser criado sem produtos selecionados");
            return ResponseEntity.noContent().build();
        } else {
            return orderPresentation.create(order);
        }
    }
}
