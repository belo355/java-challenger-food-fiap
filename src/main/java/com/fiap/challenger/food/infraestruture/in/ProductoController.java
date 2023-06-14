package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.application.domain.model.form.ProductoFormDto;
import com.fiap.challenger.food.application.domain.service.ProductoService;
import com.fiap.challenger.food.common.CategoriaEnum;
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


    @GetMapping(path = "/product/category")
    @Transactional
    public ResponseEntity findByCategory(@RequestBody CategoriaEnum categoriaEnum) {
        return productoService.findByCategory(categoriaEnum);
    }


    @DeleteMapping(path = "/product/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable Long id) {
        return productoService.remove(id);
    }
}
