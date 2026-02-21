package com.twigg.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public MessageDto sayHello() {
        return new MessageDto("Hello from Spring Boot!");
    }
    public record MessageDto(String messsage) {}
}
