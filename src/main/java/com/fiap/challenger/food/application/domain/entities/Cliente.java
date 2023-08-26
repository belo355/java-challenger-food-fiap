package com.fiap.challenger.food.application.domain.entities;

import com.fiap.challenger.food.common.form.ClienteFormDto;

public class Cliente {
    private String name;
    private int age;
    private String mail;
    private String document;

    public Cliente() {}

    public Cliente(ClienteFormDto clienteFormDto) {
        this.name = clienteFormDto.getName();
        this.age = clienteFormDto.getAge();
        this.mail = clienteFormDto.getMail();
        this.document = clienteFormDto.getDocument();
    }
    public String getMail() {
        return mail;
    }

    public String getDocument(){
        return document;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
