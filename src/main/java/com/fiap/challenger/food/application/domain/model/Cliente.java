package com.fiap.challenger.food.application.domain.model;

import com.fiap.challenger.food.common.form.ClienteFormDto;

public class Cliente {

    private String name;
    private int age;
    private String mail;
    private String document;

    public Cliente() {}

    public Cliente(String name, int age, String mail, String document) {
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.document = document;
    }

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
