package com.fiap.challenger.food.application.domain.entities;

import com.fiap.challenger.food.common.form.ProductoFormDto;
import com.fiap.challenger.food.common.CategoryEnum;

import java.math.BigDecimal;

public class Producto {
    private String description;
    private String brand;
    private CategoryEnum category;
    private BigDecimal valor;

    public Producto(ProductoFormDto productoFormDto) {
        this.description = productoFormDto.getDescription();
        this.brand = productoFormDto.getBrand();
        this.category = productoFormDto.getCategory();
        this.valor = productoFormDto.getValor();
    }

    public Producto(){}

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public BigDecimal getValor() {
        return valor;
    }

}
