package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.application.domain.model.Order;
import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.application.domain.model.form.OrderFormDto;
import com.fiap.challenger.food.application.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    private OrderService orderService;

    private Map<String, Order> orders = new HashMap<>();

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(path = "/order")
    @Transactional
    public ResponseEntity findAll() {
        return orderService.findAll();
    }

    @PostMapping(path = "/order/create")
    @Transactional
    public ResponseEntity create(@RequestBody OrderFormDto orderFormDto) {
        Order order = new Order();
        order.setStatus(OrderStatus.RECEBIDO);
        return orderService.create(orderFormDto);

    }
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id) {
        Order order = orders.get(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/order/{id}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String id, @RequestBody OrderStatus newStatus) {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatus(newStatus);
            return ResponseEntity.ok("Status do pedido atualizado: " + order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        Order order = orders.remove(id);
        if (order != null) {
            return ResponseEntity.ok("Pedido removido com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
