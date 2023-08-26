package com.fiap.challenger.food.application.domain.useCase.producto;

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
public class MakeRemoveProductoExistingUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MakeRemoveProductoExistingUseCase.class);

    private final ProductoRepository productoRepository;

    @Autowired
    public MakeRemoveProductoExistingUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ResponseEntity remove(Long id) {
        Optional<ProductoRepositoryDb> product = productoRepository.findById(id);
        if (product.isPresent()) {
            productoRepository.delete(product.get());
            logger.info("Produto removido com sucesso");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Produto nao existe", HttpStatus.NO_CONTENT);
        }
    }
}
