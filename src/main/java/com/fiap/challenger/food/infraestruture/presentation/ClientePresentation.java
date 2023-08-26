package com.fiap.challenger.food.infraestruture.presentation;

import com.fiap.challenger.food.application.domain.entities.Cliente;
import com.fiap.challenger.food.infraestruture.out.ClienteRepository;
import com.fiap.challenger.food.infraestruture.repository.ClienteRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ClientePresentation {

    private static final Logger logger = LoggerFactory.getLogger(ClientePresentation.class);

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClientePresentation(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    public ResponseEntity<Long> registre(Cliente cliente) {
        try {
            ClienteRepositoryDb clienteRepositoryDb = new ClienteRepositoryDb(cliente);
            clienteRepository.save(clienteRepositoryDb);
            logger.info("Cliente registrado com sucesso");
            Optional<ClienteRepositoryDb> c = clienteRepository.findByDocument(cliente.getDocument());
            return ResponseEntity.ok(c.get().getId());
        } catch (Exception e) {
            logger.info("Erro ao registrar novo cliente");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity findAll() {
        return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
    }
}
