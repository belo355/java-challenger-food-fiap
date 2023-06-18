package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.application.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
