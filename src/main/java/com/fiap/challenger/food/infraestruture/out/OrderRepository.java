package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.common.StatusOrderEnum;
import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderRepositoryDb, Long> {
    Optional<List<OrderRepositoryDb>> findByStatusOrderEnum(StatusOrderEnum statusOrderEnum);

}
