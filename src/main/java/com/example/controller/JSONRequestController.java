package com.example.controller;

import com.example.model.Cat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/JSONRequest")
public class JSONRequestController {

    @PostMapping
    public String jsonCats(@RequestBody Cat cat) {
        return String.format("%s the cat is %s", cat.getName(), cat.getAbility());
    }

}
