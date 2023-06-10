package com.fiap.challenger.food.application.domain.model.form;

public class ClienteForm {

    private final String name;
    private final int age;
    private final String mail;

    private final String document;

    public ClienteForm(String name, int age, String mail, String document) {
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMail() {
        return mail;
    }

    public String getDocument() {
        return document;
    }
}
