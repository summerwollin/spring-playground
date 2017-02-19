package com.example;

import com.example.controller.JSONRequestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONRequestController.class)
public class JSONRequestTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void canSendStringLiteral() throws Exception {
        MockHttpServletRequestBuilder request = post("/JSONRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Calypso\", \"ability\": \"the squeaker of squeaks\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Calypso the cat is the squeaker of squeaks"));
    }

    @Test
    public void canSendGSON() throws Exception {

        JsonObject cat = new JsonObject();
        cat.addProperty("name", "Sugar");
        cat.addProperty("ability", "the laziest lounger");

        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(cat);

        MockHttpServletRequestBuilder request = post("/JSONRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Sugar the cat is the laziest lounger"));
    }

    @Test
    public void canSendFile() throws Exception {
        String json = getJSON("/cat.json");

        MockHttpServletRequestBuilder request = post("/JSONRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Sabrina the cat is the sweet lard of lards"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }


}
