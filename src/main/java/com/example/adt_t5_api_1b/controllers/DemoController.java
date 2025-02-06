package com.example.adt_t5_api_1b.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludos/")
public class DemoController {

    // localhost:8080/saludos/hola
    @GetMapping("hola")
    String hola(){
        return "Hola mundo";
    }

    @RequestMapping("buenosdias")
    String buenosDias(){
        return "Buenos días a todos";
    }
}
