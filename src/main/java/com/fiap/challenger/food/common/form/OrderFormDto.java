package com.fiap.challenger.food.common.form;

import com.fiap.challenger.food.application.domain.entities.Cliente;
import com.fiap.challenger.food.application.domain.entities.Producto;

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

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

}
