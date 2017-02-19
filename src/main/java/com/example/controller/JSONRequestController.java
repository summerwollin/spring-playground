package com.example.controller;

import com.example.model.Owner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/JSONRequest")
public class JSONRequestController {

    @PostMapping
    public String jsonCats(@RequestBody Owner owner) {
        return owner.getCats().get(0).getName();
    }

}
