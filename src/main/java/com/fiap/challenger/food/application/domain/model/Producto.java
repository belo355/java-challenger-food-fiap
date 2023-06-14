package com.fiap.challenger.food.application.domain.model;

import com.fiap.challenger.food.application.domain.model.form.ProductoFormDto;
import com.fiap.challenger.food.common.CategoriaEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private String brand;
    private CategoriaEnum category;
    private String valor;

    public Producto(ProductoFormDto productoFormDto) {
        this.description = productoFormDto.getDescription();
        this.brand = productoFormDto.getBrand();
        this.category = productoFormDto.getCategory();
        this.valor = productoFormDto.getValor();
    }

    public Producto(){}
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public CategoriaEnum getCategory() {
        return category;
    }

    public String getValor() {
        return valor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(CategoriaEnum category) {
        this.category = category;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
