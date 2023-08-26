package com.fiap.challenger.food.application.domain.useCase.producto;

import com.fiap.challenger.food.application.domain.entities.Producto;
import com.fiap.challenger.food.common.form.ProductoFormDto;
import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import com.fiap.challenger.food.infraestruture.repository.ProductoRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MakeCreateNewProductoUseCase {

    private final ProductoRepository productoRepository;
    private static final Logger logger = LoggerFactory.getLogger(MakeCreateNewProductoUseCase.class);

    @Autowired
    public MakeCreateNewProductoUseCase(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }
    public ResponseEntity<HttpStatus> create(ProductoFormDto productoFormDto) {
        Producto producto = new Producto(productoFormDto);
        if (productoRepository.findByDescription(producto.getDescription()).isPresent()) {
            logger.info("Produto ja cadastrado anteriormente");
            return ResponseEntity.noContent().build();
        } else {
            productoRepository.save(new ProductoRepositoryDb(producto));
            logger.info("Novo produto cadastrado com sucesso: {}", producto.getDescription());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
