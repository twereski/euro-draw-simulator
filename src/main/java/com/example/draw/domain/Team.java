package com.example.draw.domain;

import lombok.Value;

@Value
public class Team {
    private final String name;

    public Team(String name) {
        this.name = name;
    }
}
