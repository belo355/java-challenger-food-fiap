package com.fiap.challenger.food.application.domain.service;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.application.domain.model.form.ClienteFormDto;
import com.fiap.challenger.food.infraestruture.out.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistreClienteService {

    private static final Logger logger = LoggerFactory.getLogger(RegistreClienteService.class);
    private final ClienteRepository clienteRepository;

    @Autowired()
    public RegistreClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ResponseEntity<HttpStatus> registre(ClienteFormDto clienteFormDto) {
        Optional<Cliente> optionalCliente = findByCPF(clienteFormDto.getDocument());
        if (!optionalCliente.isPresent()) {
//            Cliente cliente = mapper.toMapperCliente(clienteForm); //TODO: ajustar inject mapper
            Cliente cliente = new Cliente(clienteFormDto);
            this.save(cliente);
            logger.info("salvando cliente...");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        logger.info("cliente ja possui cadastro");
        return ResponseEntity.badRequest().build();
    }

    private Optional<Cliente> findByCPF(String document) {
        try {
            return clienteRepository.findByDocument(document);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar novo cliente: {}" + " cause: {} ", document, e.getMessage());
            return Optional.empty();
        }
    }

    private void save(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar novo cliente: {}" + "cause: {} ", cliente.getMail(), e.getCause());
        }
    }
}
