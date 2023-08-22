package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.application.domain.model.Order;
import com.fiap.challenger.food.common.form.OrderFormDto;
import com.fiap.challenger.food.infraestruture.out.OrderRepository;
import com.fiap.challenger.food.infraestruture.presentation.OrderPresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeCreateNewOrderUseCase {

    private final OrderPresentation orderPresentation;
    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(MakeCreateNewOrderUseCase.class);

    public MakeCreateNewOrderUseCase(OrderPresentation orderPresentation, OrderRepository orderRepository) {
        this.orderPresentation = orderPresentation;
        this.orderRepository = orderRepository;
    }
    public ResponseEntity<Long> create(OrderFormDto orderFormDto) {
        //TODO: RETIRAR A NECESSIDADE DE UM CLIENTE PARA CRIAR UM PEDIDO
        Order order = new Order(orderFormDto);
        if (order.getProductos().isEmpty()) {
            logger.info("pedido nao pode ser criado sem produtos selecionados");
            return ResponseEntity.noContent().build();
        } else {
            return createNewOrder(order);
        }
    }

    private ResponseEntity<Long> createNewOrder(Order order) {
        try {
            orderRepository.save(order);
            logger.info("Pedido criado com sucesso");
            Optional<Order> orderId = orderRepository.findById(order.getId());
            if(orderId.isPresent()){
                return orderPresentation.created(orderId.get().getId());
            }
        } catch (Exception e) {
            logger.error("Erro ao cadastrar novo pedido, cause: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return null;
    }
}
