package com.fiap.challenger.food.common.dto;

import com.fiap.challenger.food.application.domain.entities.Producto;
import com.fiap.challenger.food.common.CategoryEnum;

import java.math.BigDecimal;

public class ProductoDto {
    private String description;
    private String brand;
    private CategoryEnum category;
    private BigDecimal valor;

    public ProductoDto(){}

    public ProductoDto(Producto producto){
        this.description = producto.getDescription();
        this.brand = producto.getBrand();
        this.category = producto.getCategory();
        this.valor = producto.getValor();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
