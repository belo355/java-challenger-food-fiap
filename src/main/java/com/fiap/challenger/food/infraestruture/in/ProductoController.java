package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.common.form.ProductoFormDto;
import com.fiap.challenger.food.application.domain.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping(path = "/product/create")
    @Transactional
    public ResponseEntity<HttpStatus> create(@RequestBody ProductoFormDto productoFormDto) {
        return productoService.create(productoFormDto);
    }

    @PutMapping(path = "/product/edit/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody ProductoFormDto productoFormDto) {
        return productoService.update(id, productoFormDto);
    }

    @GetMapping(path = "/product")
    @Transactional
    public ResponseEntity findAll() {
        return productoService.findAll();
    }


    @GetMapping(path = "/product/category/{category}")
    @Transactional
    public ResponseEntity findByCategory(@PathVariable String category) {
        return productoService.findByCategory(category);
    }


    @DeleteMapping(path = "/product/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable Long id) {
        return productoService.remove(id);
    }

    @PostMapping(path = "/product/checkout")
    @Transactional
    public ResponseEntity orderCheckoutProducts(@RequestBody ProductoFormDto productoFormDto) {
        return productoService.checkout(productoFormDto);
    }
}
