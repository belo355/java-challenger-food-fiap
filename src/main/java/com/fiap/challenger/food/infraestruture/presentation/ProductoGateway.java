package com.fiap.challenger.food.infraestruture.presentation;

import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ProductoGateway {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoGateway(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ResponseEntity findAll() {
        return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
    }
}
