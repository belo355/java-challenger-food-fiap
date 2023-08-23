package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.application.domain.useCase.order.ListAllOrdersUseCase;
import com.fiap.challenger.food.application.domain.useCase.order.MakeAddIngredientIntoOrderExistsUseCase;
import com.fiap.challenger.food.application.domain.useCase.order.MakeCreateNewOrderUseCase;
import com.fiap.challenger.food.common.dto.CheckoutDto;
import com.fiap.challenger.food.common.dto.OrderDto;
import com.fiap.challenger.food.common.form.OrderFormDto;
import com.fiap.challenger.food.common.form.CheckoutOrderFormDto;
import com.fiap.challenger.food.application.domain.useCase.order.MakeCheckoutOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class OrderController {

    private final MakeCheckoutOrderUseCase makeCheckoutOrderCaseUse;
    private final ListAllOrdersUseCase listAllOrdersUseCase;
    private final MakeCreateNewOrderUseCase makeCreateNewOrderUseCase;
    private final MakeAddIngredientIntoOrderExistsUseCase makeAddIngredientIntoOrderExistsUseCase;

    @Autowired
    public OrderController(MakeCheckoutOrderUseCase makeCheckoutOrderCaseUse,
                           ListAllOrdersUseCase listAllOrdersUseCase,
                           MakeCreateNewOrderUseCase makeCreateNewOrderUseCase,
                           MakeAddIngredientIntoOrderExistsUseCase makeAddIngredientIntoOrderExistsUseCase) {
        this.makeCheckoutOrderCaseUse = makeCheckoutOrderCaseUse;
        this.listAllOrdersUseCase = listAllOrdersUseCase;
        this.makeCreateNewOrderUseCase = makeCreateNewOrderUseCase;
        this.makeAddIngredientIntoOrderExistsUseCase = makeAddIngredientIntoOrderExistsUseCase;
    }

    @GetMapping(path = "/order")
    @Transactional
    public ResponseEntity<List<OrderDto>> findAll() {
        return listAllOrdersUseCase.list();
    }

    @PostMapping(path = "/order/create")
    @Transactional
    public ResponseEntity<Long> create(@RequestBody OrderFormDto orderFormDto) {
        return makeCreateNewOrderUseCase.create(orderFormDto);
    }

    @PostMapping(path = "/order/add/ingredient/{orderId}/{ingredientId}")
    @Transactional
    public ResponseEntity addIngredient(@PathVariable Long orderId, @PathVariable Long ingredientId) {
        return makeAddIngredientIntoOrderExistsUseCase.addIngredient(orderId, ingredientId);
    }

    @PostMapping(path = "/order/checkout")
    @Transactional
    public ResponseEntity<CheckoutDto> checkout(@RequestBody CheckoutOrderFormDto resumeOrder) {
        return makeCheckoutOrderCaseUse.checkout(resumeOrder);
    }

}
