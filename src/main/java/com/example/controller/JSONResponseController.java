package com.example.controller;

import com.example.model.Cat;
import com.example.model.Owner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/JSONResponse")
public class JSONResponseController {

    @GetMapping
    public Owner getOwner() throws Exception {
        List<Cat> cats = new ArrayList<>();

        Cat jenny = new Cat();
        jenny.setName("Jenny");
        jenny.setAbility("the destroyer of worlds");

        Cat calypso = new Cat();
        calypso.setName("Calypso");
        calypso.setAbility("the squeakiest of squeaks");

        cats.add(jenny);
        cats.add(calypso);

        Owner owner = new Owner();
        owner.setName("Summer");
        owner.setCats(cats);
        return owner;
    }

}
