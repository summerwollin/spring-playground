package com.example.controller;

import com.example.model.Cat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/formData")
public class FormDataController {

    @PostMapping("/stringCats")
    public String stringCats(@RequestBody String rawBody) {
        return rawBody;
    }

    @PostMapping(value = "/mapCats", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String mapCats(@RequestParam Map<String, String> cats) {
        return cats.toString();
    }

    @PostMapping(value = "/objectCats", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String objectCats(Cat cat) {
        return String.format("%s the cat is %s", cat.getName(), cat.getAbility());
    }

}
