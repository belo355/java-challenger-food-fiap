package com.fiap.challenger.food.application.domain.model.mapper;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.application.domain.model.form.ClienteFormDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {

    @Mapping(target = "name", source = "clienteForm.name")
    @Mapping(target = "age", source = "clienteForm.age")
    @Mapping(target = "mail", source = "clienteForm.mail")
    @Mapping(target = "document", source = "clienteForm.document")
    public abstract Cliente toMapperCliente(ClienteFormDto clienteFormDto);
}
