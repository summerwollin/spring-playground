package com.example;

import com.example.controller.AlbumsController;
import com.example.model.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumsController.class)
public class AlbumsControllerTests {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private AlbumRepository repository;

    @Test
    public void canCreateAlbum() throws Exception {
        MockHttpServletRequestBuilder request = post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Tago Mago\", \"bandName\": \"Can\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.name", is("Tago Mago")))
                .andExpect(jsonPath("$.bandName", is("Can")));

        verify(this.repository).save(any(Album.class));
    }

    @Test
    public void canGetAlbums() throws Exception {
        Album album = new Album();
        album.setName("Silent Shout");
        album.setBandName("The Knife");

        when(this.repository.findAll()).thenReturn(Collections.singletonList(album));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/albums");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("Silent Shout")))
                .andExpect(jsonPath("$[0].bandName", is("The Knife")));
    }

}
