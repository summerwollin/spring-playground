package com.example.controller;

import com.example.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/queryString")
public class QueryStringController {


    @GetMapping("/nameExample")
    public String getByName(@RequestParam String item) {
        return String.format("Item is : %s", item);
    }

    @GetMapping("/mapExample")
    public String getByMap(@RequestParam Map querystring) {
        return querystring.toString();
    }

    @GetMapping("/objectExample")
    public String getByObject(Student student) {
        return String.format("name is %s, grade is %s", student.getName(), student.getGrade());
    }

}
