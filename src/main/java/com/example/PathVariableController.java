package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pathVariable")
public class PathVariableController {

    @GetMapping("/nameExample/{genre}/{title}")
    public String getByName(@PathVariable String genre, @PathVariable String title) {
        return String.format("Genre is : %s, title is : %s", genre, title);
    }

    @GetMapping("/mapExample/{exampleId}/comments/{commentId}")
    public String getByMap(@PathVariable Map pathVariables) {
        return pathVariables.toString();
    }

    @GetMapping("/objectExample/{name}/{grade}")
    public String getByObject(Student student) {
        return String.format("name is %s, grade is %s", student.getName(), student.getGrade());
    }

}
