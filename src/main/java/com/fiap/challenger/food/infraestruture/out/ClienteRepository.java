package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.application.domain.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByDocument(String document);
}
