package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.common.dto.CheckoutDto;
import com.fiap.challenger.food.common.form.OrderFormDto;
import com.fiap.challenger.food.common.form.CheckoutOrderFormDto;
import com.fiap.challenger.food.application.domain.service.OrderService;
import com.fiap.challenger.food.application.domain.useCase.MakeCheckoutOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class OrderController {

    private final OrderService orderService;

    private final MakeCheckoutOrderUseCase makeCheckoutOrderCaseUse;

    @Autowired
    public OrderController(OrderService orderService, MakeCheckoutOrderUseCase makeCheckoutOrderCaseUse){
        this.makeCheckoutOrderCaseUse = makeCheckoutOrderCaseUse;
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

    @PostMapping(path = "/order/checkout")
    @Transactional
    public ResponseEntity<CheckoutDto> checkout(@RequestBody CheckoutOrderFormDto resumeOrder) {
        return makeCheckoutOrderCaseUse.checkout(resumeOrder);
    }

}
