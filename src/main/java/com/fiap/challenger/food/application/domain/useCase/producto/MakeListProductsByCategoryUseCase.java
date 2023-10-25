package com.fiap.challenger.food.application.domain.useCase.producto;

import com.fiap.challenger.food.common.CategoryEnum;
import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import com.fiap.challenger.food.infraestruture.repository.ProductoRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeListProductsByCategoryUseCase {
    private final ProductoRepository productoRepository;
    private static final Logger logger = LoggerFactory.getLogger(MakeCreateNewProductoUseCase.class);

    @Autowired
    public MakeListProductsByCategoryUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ResponseEntity findByCategory(String category) {
        CategoryEnum categoryEnum1 = CategoryEnum.valueOf(category.toUpperCase());
        Optional<List<ProductoRepositoryDb>> products = productoRepository.findByCategory(categoryEnum1); //todo:-clealterar para presentation
        if (products.isPresent() && !products.get().isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Produtos nao encontrados para esta categoria", HttpStatus.NO_CONTENT);
        }
    }
}
