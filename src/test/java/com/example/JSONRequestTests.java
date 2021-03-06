package com.example;

import com.example.controller.JSONRequestController;
import com.example.model.Cat;
import com.example.model.Owner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.ArrayList;
import java.util.List;

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
                .content("{ \"name\": \"Summer\", \"cats\": [{\"name\": \"Jenny\", \"ability\": \"the destroyer of worlds\"}, {\"name\": \"Calypso\", \"ability\": \"the squeakiest squeaker\"}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Jenny"));
    }

    @Test
    public void canSendGSON() throws Exception {

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

        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(owner);

        MockHttpServletRequestBuilder request = post("/JSONRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Jenny"));
    }

    @Test
    public void canSendFile() throws Exception {
        String json = getJSON("/owner.json");

        MockHttpServletRequestBuilder request = post("/JSONRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Jenny"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }


}
