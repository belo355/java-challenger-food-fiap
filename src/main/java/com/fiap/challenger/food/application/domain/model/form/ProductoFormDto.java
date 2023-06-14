package com.fiap.challenger.food.application.domain.model.form;

import com.fiap.challenger.food.common.CategoriaEnum;

public class ProductoFormDto {

    private String description;
    private String brand;
    private CategoriaEnum category;
    private String valor;

    public ProductoFormDto(){}

    public ProductoFormDto(String description, String brand, CategoriaEnum category, String valor) {
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

    public CategoriaEnum getCategory() {
        return category;
    }

    public void setCategory(CategoriaEnum category) {
        this.category = category;
    }

    public String getValor() {
        return valor;
    }

    public void setValueUnity(String valor) {
        this.valor = valor;
    }
}
