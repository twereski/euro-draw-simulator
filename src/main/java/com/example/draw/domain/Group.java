package com.example.draw.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Group {

    @Getter
    private final String name;

    private final int capacity;
    @Getter
    private List<Team> teams = new ArrayList<>();

    public Group(String name) {
        this.name = name;
        capacity = 5;
    }

    void addTeam(Team team) {
        if(teams.size() == capacity) {
            throw new DomainException();
        }
        teams.add(team);
    }
}
