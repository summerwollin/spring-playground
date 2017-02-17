package com.example;

import com.example.controller.FormDataController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(FormDataController.class)
public class FormDataControllerTests {

    @Autowired
    private MockMvc mvc;


    @Test
    public void canCreateCatString() throws Exception {
        String name = "Jenny", ability = "the destroyer of worlds";

        MockHttpServletRequestBuilder request = post("/formData/stringCats")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .param("ability", ability);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("name=Jenny&ability=the+destroyer+of+worlds"));
    }

    @Test
    public void canCreateCatMap() throws Exception {
        String name = "Jenny", ability = "the destroyer of worlds";

        MockHttpServletRequestBuilder request = post("/formData/mapCats")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .param("ability", ability);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("{name=%s, ability=%s}", name, ability)));
    }

    @Test
    public void canCreateCatObject() throws Exception {
        String name = "Jenny", ability = "the destroyer of worlds";

        MockHttpServletRequestBuilder request = post("/formData/objectCats")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .param("ability", ability);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("%s the cat is %s", name, ability)));
    }
}
