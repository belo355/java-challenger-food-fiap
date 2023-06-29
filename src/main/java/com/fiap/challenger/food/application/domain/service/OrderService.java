package com.fiap.challenger.food.application.domain.service;

import com.fiap.challenger.food.application.domain.model.Order;
import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.application.domain.model.dto.OrderDto;
import com.fiap.challenger.food.application.domain.model.form.OrderFormDto;
import com.fiap.challenger.food.infraestruture.out.OrderRepository;
import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductoRepository productoRepository) {
        this.orderRepository = orderRepository;
        this.productoRepository = productoRepository;
    }

    public ResponseEntity<List<OrderDto>> findAll() {
        Iterable<Order> orders = orderRepository.findAll();
        List<Order> orderList = new ArrayList<>();
        List<OrderDto> orderDtoList = new ArrayList<>();
        orders.forEach(orderList::add);
        orderList.forEach(order -> {
            OrderDto dto = new OrderDto(order);
            orderDtoList.add(dto);
        });
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    public ResponseEntity create(OrderFormDto orderFormDto) {
        Order order = new Order(orderFormDto);
        LocalDateTime dateOrder = LocalDateTime.now();
        order.setDateOrder(dateOrder);
        if (order.getProductos().isEmpty()) {
            logger.info("pedido nao pode ser criado sem produtos selecionados");
            return ResponseEntity.noContent().build();
        } else {
            return createNewOrder(order);
        }

    }

    private ResponseEntity createNewOrder(Order order) {
        try {
            orderRepository.save(order);
            logger.info("Pedido criado com sucesso");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar novo pedido, cause: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<OrderDto> addIngredient(OrderFormDto orderFormDto, Long ingredient) {
        Optional<Producto> productoIngredient = productoRepository.findById(ingredient);
        if(productoIngredient.isPresent()){
            List<Producto> products = orderFormDto.getProductoList();
            products.add(productoIngredient.get());
            orderFormDto.setProductoList(products);
            OrderDto orderDto = new OrderDto(orderFormDto);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
