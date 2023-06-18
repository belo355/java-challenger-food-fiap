package com.fiap.challenger.food.infraestruture.in;

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

@RestController
public class OrderController {

    private OrderService orderService;

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
        return orderService.create(orderFormDto);
    }
}
