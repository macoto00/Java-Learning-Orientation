package com.example.basicwebshop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetWebshop {
    @RequestMapping(value ="/webshop")
    public String hello() {
        return "Hello World";
    }
}
