package com.fiap.challenger.food.infraestruture.in;

import com.fiap.challenger.food.application.domain.useCase.cliente.MakeRegistreNewClientUseCase;
import com.fiap.challenger.food.common.form.ClienteFormDto;
import com.fiap.challenger.food.infraestruture.gateway.ClienteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class ClienteController {

    private final MakeRegistreNewClientUseCase makeRegistreNewClientUseCase;
    private final ClienteGateway gateway;

    @Autowired
    public ClienteController(MakeRegistreNewClientUseCase makeRegistreNewClientUseCase,
                             ClienteGateway clienteGateway) {
        this.makeRegistreNewClientUseCase = makeRegistreNewClientUseCase;
        this.gateway = clienteGateway;
    }

    @PostMapping(path = "/cliente/registre")
    @Transactional
    public ResponseEntity<Long> create(@RequestBody ClienteFormDto clienteFormDto) {
        return makeRegistreNewClientUseCase.registre(clienteFormDto);
    }

    @GetMapping(path = "/cliente")
    @Transactional
    public ResponseEntity findAll() {
        return gateway.findAll();
    }
}
