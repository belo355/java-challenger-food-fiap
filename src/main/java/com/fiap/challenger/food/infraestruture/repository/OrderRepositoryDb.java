package com.fiap.challenger.food.infraestruture.repository;

import com.fiap.challenger.food.application.domain.model.Order;
import com.fiap.challenger.food.application.domain.model.Producto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERPAYMENT")
public class OrderRepositoryDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOrder;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Producto> products;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private ClienteRepositoryDb cliente;


    public OrderRepositoryDb(Order order) {
        this.products = order.getProductos();
        this.dateOrder = order.getDateOrder();
        if (order.getCliente().getDocument() == null) {
            this.cliente = new ClienteRepositoryDb();
        } else {
            this.cliente = new ClienteRepositoryDb(order.getCliente());
        }
    }

    public OrderRepositoryDb(){}

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public List<Producto> getProductos() {
        return products;
    }

    public ClienteRepositoryDb getCliente() {
        return cliente;
    }

    public void setProducts(Producto product) {
        this.products.add(product);
    }
}
