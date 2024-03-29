package com.fiap.challenger.food.application.domain.useCase.cliente;

import com.fiap.challenger.food.application.domain.entities.Cliente;
import com.fiap.challenger.food.common.form.ClienteFormDto;
import com.fiap.challenger.food.infraestruture.out.ClienteRepository;
import com.fiap.challenger.food.infraestruture.gateway.ClienteGateway;
import com.fiap.challenger.food.infraestruture.repository.ClienteRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeRegistreNewClientUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MakeRegistreNewClientUseCase.class);
    private final ClienteGateway clienteGateway;
    private final ClienteRepository clienteRepository;

    @Autowired
    public MakeRegistreNewClientUseCase(ClienteGateway clienteGateway, ClienteRepository clienteRepository) {
        this.clienteGateway = clienteGateway;
        this.clienteRepository = clienteRepository;
    }

    public ResponseEntity<Long> registre(ClienteFormDto clienteFormDto) {
        Optional<ClienteRepositoryDb> optionalCliente = clienteRepository.findByDocument(clienteFormDto.getDocument());
        if (!optionalCliente.isPresent()) {
            Cliente cliente = new Cliente(clienteFormDto);
            return clienteGateway.registre(cliente);
        }
        logger.info("Cliente ja possui cadastro");
        return ResponseEntity.badRequest().build();
    }
}