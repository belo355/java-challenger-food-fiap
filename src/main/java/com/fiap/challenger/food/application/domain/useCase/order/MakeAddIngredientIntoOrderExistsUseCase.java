package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.infraestruture.out.OrderRepository;
import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import com.fiap.challenger.food.infraestruture.gateway.OrderGateway;
import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import com.fiap.challenger.food.infraestruture.repository.ProductoRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeAddIngredientIntoOrderExistsUseCase {

    private final OrderGateway gateway;
    private final OrderRepository orderRepository;
    private final ProductoRepository productoRepository;
    private static final Logger logger = LoggerFactory.getLogger(MakeAddIngredientIntoOrderExistsUseCase.class);

    @Autowired
    public MakeAddIngredientIntoOrderExistsUseCase(OrderGateway orderGateway, OrderRepository orderRepository, ProductoRepository productoRepository) {
        this.gateway = orderGateway;
        this.orderRepository = orderRepository;
        this.productoRepository = productoRepository;
    }

    public ResponseEntity addIngredient(Long orderId, Long ingredientId) {
        Optional<ProductoRepositoryDb> productoIngredient = productoRepository.findById(ingredientId);
        Optional<OrderRepositoryDb> order = orderRepository.findById(orderId);
        if (productoIngredient.isPresent() && order.isPresent()) {
            order.get().setProducts(productoIngredient.get());
            return gateway.addIngredient();
        } else {
            logger.error("Produto ou ingrediente inexistente");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
