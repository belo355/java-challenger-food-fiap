package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.application.domain.model.form.OrderFormDto;
import com.fiap.challenger.food.application.domain.model.form.ProductoFormDto;
import com.fiap.challenger.food.application.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/order/add/ingredient/{ingredientId}")
    @Transactional
    public ResponseEntity addIngredient(@RequestBody OrderFormDto orderFormDto, @PathVariable Long ingredientId) {
        return orderService.addIngredient(orderFormDto, ingredientId);
    }
}
