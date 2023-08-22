package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.application.domain.model.Order;
import com.fiap.challenger.food.common.dto.OrderDto;
import com.fiap.challenger.food.infraestruture.out.OrderRepository;
import com.fiap.challenger.food.infraestruture.presentation.OrderPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllOrdersUseCase {

    private final OrderPresentation orderPresentation;
    private final OrderRepository orderRepository;

    @Autowired
    public ListAllOrdersUseCase(OrderPresentation orderPresentation, OrderRepository orderRepository) {
        this.orderPresentation = orderPresentation;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<List<OrderDto>> list() {
        Iterable<Order> orders = orderRepository.findAll();
        return orderPresentation.getAllOrders(orders);
    }
}
