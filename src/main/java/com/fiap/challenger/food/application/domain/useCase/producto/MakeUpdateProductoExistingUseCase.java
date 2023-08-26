package com.fiap.challenger.food.application.domain.useCase.producto;

import com.fiap.challenger.food.common.form.ProductoFormDto;
import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import com.fiap.challenger.food.infraestruture.repository.ProductoRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeUpdateProductoExistingUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MakeUpdateProductoExistingUseCase.class);

    private final ProductoRepository productoRepository;

    @Autowired
    public MakeUpdateProductoExistingUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ResponseEntity<HttpStatus> update(Long id, ProductoFormDto productoFormDto) {
        Optional<ProductoRepositoryDb> producto = productoRepository.findById(id);;
        if(producto.isPresent()){
            updateExistProduct(producto.get().getId(), productoFormDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            logger.info("Produto informado nao existente");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    private void updateExistProduct(Long id, ProductoFormDto productoFormDto) {
        Iterable<ProductoRepositoryDb> products = productoRepository.findAll();
        products.forEach(p -> {
            if (p.getId().equals(id)) {
                p.setDescription(productoFormDto.getDescription());
                p.setBrand(productoFormDto.getBrand());
                p.setCategory(productoFormDto.getCategory());
                p.setValor(productoFormDto.getValor());
            }
        });
    }
}
