package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.application.domain.model.Order;
import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.common.dto.OrderDto;
import com.fiap.challenger.food.infraestruture.out.OrderRepository;
import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import com.fiap.challenger.food.infraestruture.presentation.OrderPresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeAddIngredientIntoOrderExistsUseCase {

    private final OrderPresentation orderPresentation;
    private final OrderRepository orderRepository;
    private final ProductoRepository productoRepository;
    private static final Logger logger = LoggerFactory.getLogger(MakeAddIngredientIntoOrderExistsUseCase.class);

    @Autowired
    public MakeAddIngredientIntoOrderExistsUseCase(OrderPresentation orderPresentation, OrderRepository orderRepository, ProductoRepository productoRepository) {
        this.orderPresentation = orderPresentation;
        this.orderRepository = orderRepository;
        this.productoRepository = productoRepository;
    }


    public ResponseEntity addIngredient(Long orderId, Long ingredientId) {
        Optional<Producto> productoIngredient = productoRepository.findById(ingredientId);
        Optional<Order> order = orderRepository.findById(orderId);
        if(productoIngredient.isPresent() && order.isPresent()){
            order.get().setProducts(productoIngredient.get());
            return orderPresentation.addIngredient();
        }else{
            logger.error("Produto ou ingrediente inexistente");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
