package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.application.domain.useCase.producto.MakeCreateNewProductoUseCase;
import com.fiap.challenger.food.application.domain.useCase.producto.MakeListProductsByCategoryUseCase;
import com.fiap.challenger.food.application.domain.useCase.producto.MakeRemoveProductoExistingUseCase;
import com.fiap.challenger.food.application.domain.useCase.producto.MakeUpdateProductoExistingUseCase;
import com.fiap.challenger.food.common.form.ProductoFormDto;
import com.fiap.challenger.food.infraestruture.presentation.ProductoPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class ProductoController {

    private final MakeCreateNewProductoUseCase makeCreateNewProductoUseCase;
    private final MakeUpdateProductoExistingUseCase makeUpdateProductoExistingUseCase;
    private final MakeRemoveProductoExistingUseCase makeRemoveProductoExistingUseCase;
    private final MakeListProductsByCategoryUseCase makeListProductsByCategoryUseCase;
    private final ProductoPresentation productoPresentation;

    @Autowired
    public ProductoController(MakeCreateNewProductoUseCase makeCreateNewProductoUseCase, MakeUpdateProductoExistingUseCase makeUpdateProductoExistingUseCase, MakeRemoveProductoExistingUseCase makeRemoveProductoExistingUseCase, MakeListProductsByCategoryUseCase makeListProductsByCategoryUseCase, ProductoPresentation productoPresentation){
        this.makeCreateNewProductoUseCase = makeCreateNewProductoUseCase;
        this.makeUpdateProductoExistingUseCase = makeUpdateProductoExistingUseCase;
        this.makeRemoveProductoExistingUseCase = makeRemoveProductoExistingUseCase;
        this.makeListProductsByCategoryUseCase = makeListProductsByCategoryUseCase;
        this.productoPresentation = productoPresentation;
    }

    @PostMapping(path = "/product/create")
    @Transactional
    public ResponseEntity<HttpStatus> create(@RequestBody ProductoFormDto productoFormDto) {
        return makeCreateNewProductoUseCase.create(productoFormDto);
    }

    @PutMapping(path = "/product/edit/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody ProductoFormDto productoFormDto) {
        return makeUpdateProductoExistingUseCase.update(id, productoFormDto);
    }

    @GetMapping(path = "/product")
    @Transactional
    public ResponseEntity findAll() {
        return productoPresentation.findAll();
    }


    @GetMapping(path = "/product/category/{category}")
    @Transactional
    public ResponseEntity findByCategory(@PathVariable String category) {
        return makeListProductsByCategoryUseCase.findByCategory(category);
    }


    @DeleteMapping(path = "/product/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable Long id) {
        return makeRemoveProductoExistingUseCase.remove(id);
    }

}
