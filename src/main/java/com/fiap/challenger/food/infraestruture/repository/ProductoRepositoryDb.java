package com.fiap.challenger.food.infraestruture.repository;

import com.fiap.challenger.food.application.domain.entities.Producto;
import com.fiap.challenger.food.common.CategoryEnum;
import com.fiap.challenger.food.common.form.ProductoFormDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTO")
public class ProductoRepositoryDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String brand;
    private CategoryEnum category;
    private BigDecimal valor;

    public ProductoRepositoryDb(){}

    public ProductoRepositoryDb(ProductoFormDto productoFormDto) {
        this.description = productoFormDto.getDescription();
        this.brand = productoFormDto.getBrand();
        this.category = productoFormDto.getCategory();
        this.valor = productoFormDto.getValor();
    }

    public ProductoRepositoryDb(Producto producto) {
        this.description = producto.getDescription();
        this.brand = producto.getBrand();
        this.category = producto.getCategory();
        this.valor = producto.getValor();
    }

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
