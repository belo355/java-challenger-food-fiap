package com.fiap.challenger.food.infraestruture.out;

import com.fiap.challenger.food.infraestruture.repository.ClienteRepositoryDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteRepositoryDb, Long> {
    Optional<ClienteRepositoryDb> findByDocument(String document);
}
