package com.example.controller;

import com.example.AlbumRepository;
import com.example.model.Album;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    private final AlbumRepository repo;

    public AlbumsController(AlbumRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Album> getAll() {
        return this.repo.findAll();
    }

    @PostMapping
    public Album create(@RequestBody Album album) {
        this.repo.save(album);
        return album;
    }
}
