package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.application.domain.model.Producto;
import com.fiap.challenger.food.common.CategoryEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    Optional<Producto> findByDescription(String description);

    Optional<List<Producto>> findByCategory(CategoryEnum categoryEnum);
}