package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String getHello() {
        return "Hello from Spring!";
    }

    @PostMapping
    public String createHello() {
        return "POSTed to hello!";
    }

    @PatchMapping
    public String patchHello() {
        return "PATCHed hello";
    }

    @DeleteMapping
    public String deleteHello() {
        return "DELETEd hello";
    }
}