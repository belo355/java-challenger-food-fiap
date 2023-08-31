package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.common.dto.CheckoutDto;
import com.fiap.challenger.food.common.form.CheckoutOrderFormDto;
import com.fiap.challenger.food.infraestruture.presentation.OrderGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import static java.math.BigInteger.ZERO;

@Service
public class MakeCheckoutOrderUseCase {

    private BigDecimal totalOrderCheckout = new BigDecimal(ZERO);

    private final OrderGateway gateway;

    public MakeCheckoutOrderUseCase(OrderGateway orderGateway) {
        this.gateway = orderGateway;
    }

    public ResponseEntity<CheckoutDto> checkout(CheckoutOrderFormDto checkoutOrderFormDto) {
        calcTotalCostOrder(checkoutOrderFormDto);
        return gateway.makeCheckoutOrder(checkoutOrderFormDto.getProductoList(), checkoutOrderFormDto.getCliente(), this.totalOrderCheckout);
    }

    private void calcTotalCostOrder(CheckoutOrderFormDto checkoutOrderFormDto) {
        checkoutOrderFormDto.getProductoList().forEach(p -> {
            this.totalOrderCheckout = totalOrderCheckout.add(new BigDecimal(String.valueOf(p.getValor())));
        });
    }
}
