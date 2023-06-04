package com.fiap.challenger.food.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @GetMapping
    public String findAll() {
        return "teste aqui";
    }

}
