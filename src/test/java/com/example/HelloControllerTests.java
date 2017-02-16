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

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void canGetHello() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
        this.mvc.perform(request).andExpect(content().string("Hello from Spring!"));
    }

    @Test
    public void canPostHello() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/hello");
        this.mvc.perform(request).andExpect(content().string("POSTed to hello!"));
    }

    @Test
    public void canPatchHello() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.patch("/hello");
        this.mvc.perform(request).andExpect(content().string("PATCHed hello"));
    }

    @Test
    public void canDeleteHello() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/hello");
        this.mvc.perform(request).andExpect(content().string("DELETEd hello"));
    }



}
