package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderRepositoryDb, Long> {
}
