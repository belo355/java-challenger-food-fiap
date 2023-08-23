package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.common.dto.CheckoutDto;
import com.fiap.challenger.food.common.form.CheckoutOrderFormDto;
import com.fiap.challenger.food.infraestruture.presentation.OrderPresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import static java.math.BigInteger.ZERO;

@Service
public class MakeCheckoutOrderUseCase {

    private BigDecimal totalOrderCheckout = new BigDecimal(ZERO);

    private final OrderPresentation orderPresentation;

    public MakeCheckoutOrderUseCase(OrderPresentation orderPresentation) {
        this.orderPresentation = orderPresentation;
    }

    public ResponseEntity<CheckoutDto> checkout(CheckoutOrderFormDto checkoutOrderFormDto) {
        calcTotalCostOrder(checkoutOrderFormDto);
        return orderPresentation.makeCheckoutOrder(checkoutOrderFormDto.getProductoList(), checkoutOrderFormDto.getCliente(), this.totalOrderCheckout);
    }

    private void calcTotalCostOrder(CheckoutOrderFormDto checkoutOrderFormDto) {
        checkoutOrderFormDto.getProductoList().forEach(p -> {
            this.totalOrderCheckout = totalOrderCheckout.add(new BigDecimal(String.valueOf(p.getValor())));
        });
    }
}
