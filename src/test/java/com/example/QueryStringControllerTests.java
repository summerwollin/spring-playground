package com.example;

import com.example.controller.QueryStringController;
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
@WebMvcTest(QueryStringController.class)
public class QueryStringControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void canGetByName() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/queryString/nameExample?item=testItem");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Item is : testItem"));
    }

    @Test
    public void canGetByMap() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/queryString/mapExample?item=testItem");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{item=testItem}"));
    }

    @Test
    public void canGetByObject() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/queryString/objectExample?name=Summer&grade=5");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("name is Summer, grade is 5"));
    }

}
