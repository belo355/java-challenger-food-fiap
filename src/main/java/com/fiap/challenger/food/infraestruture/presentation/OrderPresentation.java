package com.fiap.challenger.food.infraestruture.presentation;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.application.domain.model.Order;
import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.common.dto.CheckoutDto;
import com.fiap.challenger.food.common.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderPresentation {

    public ResponseEntity<CheckoutDto> makeCheckoutOrder(List<Producto> productoList, Cliente cliente, BigDecimal costTotalOrder){
        CheckoutDto checkoutDto = new CheckoutDto(productoList, cliente, costTotalOrder);
        return ResponseEntity.ok(checkoutDto);
    }


    public ResponseEntity<List<OrderDto>> getAllOrders(Iterable<Order> orders) {
        List<Order> orderList = new ArrayList<>();
        List<OrderDto> orderDtoList = new ArrayList<>();
        orders.forEach(orderList::add);
        orderList.forEach(order -> {
            OrderDto dto = new OrderDto(order);
            orderDtoList.add(dto);
        });
        return ResponseEntity.ok(orderDtoList);
    }

    public ResponseEntity<Long> created(Long orderId) {
        return ResponseEntity.ok(orderId);
    }

    public ResponseEntity addIngredient() {
        return ResponseEntity.ok().build();
    }
}
