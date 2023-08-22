package com.fiap.challenger.food.infraestruture.repository;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.common.form.OrderFormDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Entity
//@Table(name = "ORDERPAYMENT")
public class OrderRepository {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOrder;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Producto> products;

    @ManyToOne
    private Cliente cliente;

    public OrderRepository(OrderFormDto orderFormDto) {
        this.products = orderFormDto.getProductoList();
        if (orderFormDto.getCliente().getDocument().isEmpty()) {
            this.cliente = new Cliente();
        } else {
            this.cliente = orderFormDto.getCliente();
        }
    }

    public OrderRepository(){}

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
