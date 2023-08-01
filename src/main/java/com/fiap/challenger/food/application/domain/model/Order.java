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

    public Order(OrderFormDto orderFormDto) {
        this.products = orderFormDto.getProductoList();
        if (orderFormDto.getCliente().getDocument().isEmpty()) {
            this.cliente = new Cliente();
        } else {
            this.cliente = orderFormDto.getCliente();
        }
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

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }
}
