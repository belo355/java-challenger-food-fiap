package com.fiap.challenger.food.common.form;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.application.domain.model.Producto;

import java.util.List;

public class CheckoutOrderFormDto {
    private List<Producto> productoList;

    private Cliente cliente;

    public List<Producto> getProductoList() {
        return productoList;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
