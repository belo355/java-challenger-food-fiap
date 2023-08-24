package com.fiap.challenger.food.application.domain.model;

import com.fiap.challenger.food.common.form.OrderFormDto;
import com.fiap.challenger.food.infraestruture.repository.ClienteRepositoryDb;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private LocalDateTime dateOrder;
    private List<Producto> products;
    private Cliente cliente;

    public Order(OrderFormDto orderFormDto) {
        this.products = orderFormDto.getProductoList();
        this.dateOrder = LocalDateTime.now();
        if (orderFormDto.getCliente().getDocument().isEmpty()) {
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

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setProducts(List<Producto> products) {
        this.products = products;
    }

    public void setProducts(Producto p) {
        this.products.add(p);
    }
}
