package com.fiap.challenger.food.common.dto;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.application.domain.model.Producto;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutDto {
    private List<Producto> productoList;
    private Cliente cliente;
    private BigDecimal valueTotalOrder;

    public CheckoutDto(List<Producto> productoList, Cliente cliente, BigDecimal valueTotalOrder) {
        this.productoList = productoList;
        this.cliente = cliente;
        this.valueTotalOrder = valueTotalOrder;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValueTotalOrder() {
        return valueTotalOrder;
    }

    public void setValueTotalOrder(BigDecimal valueTotalOrder) {
        this.valueTotalOrder = valueTotalOrder;
    }
}
