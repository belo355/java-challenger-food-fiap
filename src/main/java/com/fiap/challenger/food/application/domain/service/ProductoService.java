package com.fiap.challenger.food.application.domain.service;

import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.common.dto.ProductoDto;
import com.fiap.challenger.food.common.form.ProductoFormDto;
import com.fiap.challenger.food.common.CategoryEnum;
import com.fiap.challenger.food.infraestruture.out.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }
    public ResponseEntity create(ProductoFormDto productoFormDto) {
        Producto producto = new Producto(productoFormDto);
        if (productoExistsByDescription(producto.getDescription())) {
            logger.info("produto ja cadastrado anteriormente");
            return ResponseEntity.noContent().build();
        } else {
            return createNewProducto(producto);
        }
    }

    private boolean productoExistsByDescription(String description) {
        try {
            return productoRepository.findByDescription(description).isPresent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseEntity createNewProducto(Producto producto){
        try {
            productoRepository.save(producto);
            logger.info("produto cadastrado com sucesso: {}", producto.getDescription());
            Long id = productoRepository.findByDescription(producto.getDescription()).get().getId();
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("erro ao cadastrar novo produto: {}" + " cause: {}", producto.getDescription(), e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<HttpStatus> update(Long id, ProductoFormDto productoFormDto) {
        Producto producto = productoExistsById(id);
        if(producto.getId() != null){
            updateExistProduct(producto.getId(), productoFormDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            logger.info("produto nao encontrado");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    private Producto productoExistsById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElseGet(Producto::new);
    }

    private void updateExistProduct(Long id, ProductoFormDto productoFormDto) {
        Iterable<Producto> products = productoRepository.findAll();
        products.forEach(p -> {
            if (p.getId().equals(id)) {
                p.setDescription(productoFormDto.getDescription());
                p.setBrand(productoFormDto.getBrand());
                p.setCategory(productoFormDto.getCategory());
                p.setValor(productoFormDto.getValor());
            }
        });
    }

    public ResponseEntity findAll() {
        Iterable<Producto> iterableProducts = productoRepository.findAll();
        List<Producto> productsFind = new ArrayList<>();
        List<ProductoDto> productsDtoList = new ArrayList<>();
        iterableProducts.forEach(productsFind::add);
        productsFind.forEach(pf -> {
            ProductoDto productoDto = new ProductoDto(pf);
            productsDtoList.add(productoDto);
        });
        return new ResponseEntity<>(productsDtoList, HttpStatus.OK);
    }

    public ResponseEntity remove(Long id) {
        Optional<Producto> product = productoRepository.findById(id);
        if (product.isPresent()) {
            productoRepository.delete(product.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Produto nao existe", HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity findByCategory(String category) {
        CategoryEnum categoryEnum1 = CategoryEnum.valueOf(category.toUpperCase());
        Optional<List<Producto>> products = productoRepository.findByCategory(categoryEnum1);
        if (products.isPresent() && !products.get().isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Produtos nao encontrados para esta categoria", HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity checkout(ProductoFormDto productoFormDto) {
        Optional<Producto> producto = productoRepository.findByDescription(productoFormDto.getDescription());
        if(!producto.isPresent()){
            logger.error("Produto nao existe, checkout nao realizado!");
        }
        logger.error("checkout realizado para o produto: {}", productoFormDto.getDescription() );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
