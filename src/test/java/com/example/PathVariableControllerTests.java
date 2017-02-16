package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PathVariableController.class)
public class PathVariableControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void canGetByName() throws Exception {
        String genre = "drama", title = "titanic";

        RequestBuilder request = MockMvcRequestBuilders.get(String.format("/pathVariable/nameExample/%s/%s", genre, title));
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Genre is : drama, title is : titanic"));
    }

    @Test
    public void canGetByMap() throws Exception {
        int exampleId = 1, commentId = 2;

        RequestBuilder request = MockMvcRequestBuilders.get(String.format("/pathVariable/mapExample/%d/comments/%d", exampleId, commentId));
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{exampleId=1, commentId=2}"));
    }

    @Test
    public void canGetByObject() throws Exception {
        Student student = new Student();
        student.setName("Lincoln");
        student.setGrade(5);

        RequestBuilder request = MockMvcRequestBuilders.get(String.format("/pathVariable/objectExample/%s/%d", student.getName(), student.getGrade()));
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("name is Lincoln, grade is 5"));
    }

}
