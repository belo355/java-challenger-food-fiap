package com.fiap.challenger.food.application.domain.model;

import com.fiap.challenger.food.common.form.ProductoFormDto;
import com.fiap.challenger.food.common.CategoryEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    public Long getId() {
        return id;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
