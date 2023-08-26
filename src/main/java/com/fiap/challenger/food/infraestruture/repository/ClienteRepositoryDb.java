package com.fiap.challenger.food.infraestruture.repository;

import com.fiap.challenger.food.application.domain.model.Cliente;
import com.fiap.challenger.food.common.form.ClienteFormDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CLIENTE")
public class ClienteRepositoryDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String mail;
    private String document;

    public ClienteRepositoryDb() {}

    public ClienteRepositoryDb(Long id, String name, int age, String mail, String document) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.document = document;
    }

    public ClienteRepositoryDb(ClienteFormDto clienteFormDto) {
        this.name = clienteFormDto.getName();
        this.age = clienteFormDto.getAge();
        this.mail = clienteFormDto.getMail();
        this.document = clienteFormDto.getDocument();
    }

    public ClienteRepositoryDb(Cliente cliente) {
        this.name = cliente.getName();
        this.age = cliente.getAge();
        this.mail = cliente.getMail();
        this.document = cliente.getDocument();
    }
    public String getMail() {
        return mail;
    }

    public String getDocument(){
        return document;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
