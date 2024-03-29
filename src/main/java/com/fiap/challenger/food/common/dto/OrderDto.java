package com.fiap.challenger.food.common.dto;

import com.fiap.challenger.food.application.domain.entities.Cliente;
import com.fiap.challenger.food.application.domain.entities.Order;
import com.fiap.challenger.food.application.domain.entities.Producto;
import com.fiap.challenger.food.common.form.OrderFormDto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private LocalDateTime dateOrder;

    private List<Producto> productoList;

    private Cliente cliente;

    public OrderDto(Order order) {
        this.dateOrder = order.getDateOrder();
        this.productoList = order.getProductos();
        this.cliente = order.getCliente();
    }

    public OrderDto(OrderFormDto orderFormDto) {
        this.cliente = orderFormDto.getCliente();
        this.productoList = orderFormDto.getProductoList();
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
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
}
