package com.fiap.challenger.food.application.domain.entities;

import com.fiap.challenger.food.common.StatusOrderEnum;
import com.fiap.challenger.food.common.form.OrderFormDto;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private LocalDateTime dateOrder;
    private List<Producto> products;
    private Cliente cliente;
    private StatusOrderEnum statusOrderEnum;

    public Order(OrderFormDto orderFormDto) {
        this.products = orderFormDto.getProductoList();
        this.dateOrder = LocalDateTime.now();
        if (orderFormDto.getCliente().getDocument() == null) {
            this.cliente = new Cliente();
        } else {
            this.cliente = orderFormDto.getCliente();
        }
    }

    public Order(){}

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public List<Producto> getProductos() {
        return (List<Producto>) products;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
