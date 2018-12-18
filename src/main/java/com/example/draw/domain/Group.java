package com.example.draw.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Group {

    @Getter
    private String name;
    private List<Team> teams = new ArrayList<Team>();

    public Group(String name) {
        this.name = name;
    }
}
