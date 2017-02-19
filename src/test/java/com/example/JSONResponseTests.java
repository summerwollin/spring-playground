package com.example;

import com.example.controller.JSONResponseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONResponseController.class)
public class JSONResponseTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testObject() throws Exception {
        MockHttpServletRequestBuilder request = get("/JSONResponse")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Summer")))
                .andExpect(jsonPath("$.cats.[0].name", is("Jenny")))
                .andExpect(jsonPath("$.cats.[0].ability", is("the destroyer of worlds")))
                .andExpect(jsonPath("$.cats.[1].name", is("Calypso")))
                .andExpect(jsonPath("$.cats.[1].ability", is("the squeakiest of squeaks")));
    }

}
