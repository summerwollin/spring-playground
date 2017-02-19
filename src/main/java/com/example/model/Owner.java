package com.example.model;

import java.util.List;

public class Owner {
    private String name;
    private List<Cat> cats;

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
