package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.common.CategoriaEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    Optional<Producto> findByDescription(String description);

    Optional<Producto> findByCategory(CategoriaEnum categoriaEnum);
}