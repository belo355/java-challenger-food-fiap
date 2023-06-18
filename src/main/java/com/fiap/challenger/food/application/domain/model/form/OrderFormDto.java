package com.fiap.challenger.food.application.domain.model.form;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.application.domain.model.Producto;

import java.util.List;

public class OrderFormDto {

    private List<Producto> productoList;

    private Cliente cliente;

    public List<Producto> getProductoList() {
        return productoList;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
