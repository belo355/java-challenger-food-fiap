package com.fiap.challenger.food.application.domain.model;

import com.fiap.challenger.food.application.domain.model.form.OrderFormDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERPAYMENT")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOrder;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Producto> products;

    @ManyToOne
    private Cliente cliente;

    private OrderStatus status;

    public Order(OrderFormDto orderFormDto) {
        this.products = orderFormDto.getProductoList();
        this.cliente = orderFormDto.getCliente();
    }

    public Order(){}

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public List<Producto> getProductos() {
        return (List<Producto>) products;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
