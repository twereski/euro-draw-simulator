package com.example.draw.domain.group;

import com.example.draw.domain.DomainException;
import com.example.draw.domain.Team;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Group {

    @Getter
    private final Character name;

    private final int capacity;
    @Getter
    private List<Team> teams = new ArrayList<>();

    public Group(Character name) {
        this.name = name;
        capacity = 5;
    }

    public void addTeam(Team team) {
        if(freePlaces() == 0) {
            throw new DomainException();
        }
        teams.add(team);
    }

    int freePlaces() {
        return capacity - teams.size();
    }
}
