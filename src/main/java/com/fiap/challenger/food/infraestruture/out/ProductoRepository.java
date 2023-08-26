package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.common.CategoryEnum;
import com.fiap.challenger.food.infraestruture.repository.ProductoRepositoryDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoRepositoryDb, Long> {
    Optional<ProductoRepositoryDb> findByDescription(String description);

    Optional<List<ProductoRepositoryDb>> findByCategory(CategoryEnum categoryEnum);
}