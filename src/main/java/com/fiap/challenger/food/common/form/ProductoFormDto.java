package com.fiap.challenger.food.common.form;

import com.fiap.challenger.food.common.CategoryEnum;

import java.math.BigDecimal;

public class ProductoFormDto {

    private String description;
    private String brand;
    private CategoryEnum category;
    private BigDecimal valor;

    public ProductoFormDto(){}

    public ProductoFormDto(String description, String brand, CategoryEnum category, BigDecimal valor) {
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.valor = valor;
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

    public void setValueUnity(BigDecimal valor) {
        this.valor = valor;
    }
}
