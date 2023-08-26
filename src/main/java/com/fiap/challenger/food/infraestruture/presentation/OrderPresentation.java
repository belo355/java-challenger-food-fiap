package com.fiap.challenger.food.infraestruture.presentation;

import com.fiap.challenger.food.application.domain.entities.Cliente;
import com.fiap.challenger.food.application.domain.entities.Order;
import com.fiap.challenger.food.application.domain.entities.Producto;
import com.fiap.challenger.food.common.dto.CheckoutDto;
import com.fiap.challenger.food.infraestruture.out.OrderRepository;
import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderPresentation {

    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderPresentation.class);


    public OrderPresentation(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    public ResponseEntity<CheckoutDto> makeCheckoutOrder(List<Producto> productoList, Cliente cliente, BigDecimal costTotalOrder){
        CheckoutDto checkoutDto = new CheckoutDto(productoList, cliente, costTotalOrder);
        return ResponseEntity.ok(checkoutDto);
    }

    public ResponseEntity findAll() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity addIngredient() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Long> create(Order order) {
        OrderRepositoryDb repositoryDb = new OrderRepositoryDb(order);
        try {
            orderRepository.save(repositoryDb);
            logger.info("Pedido criado com sucesso");
            Optional<OrderRepositoryDb> orderId = orderRepository.findById(repositoryDb.getId());
            return ResponseEntity.ok(orderId.get().getId());
        } catch (Exception e) {
            logger.error("Erro ao cadastrar novo pedido, cause: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
