package com.example.draw.domain.group;

import com.example.draw.domain.DomainException;
import com.example.draw.domain.Team;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"name"})
public class Group {

    @Getter
    private final Character name;
    @Getter
    private final int capacity;

    private List<Team> teams = new ArrayList<>();

    public Group(Character name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void addTeam(Team team) {
        if (freePlaces() == 0) {
            throw new DomainException();
        }
        teams.add(team);
    }

    public ImmutableList<Team> getTeams() {
        return ImmutableList.copyOf(teams);
    }

    public int freePlaces() {
        return capacity - teams.size();
    }

    int size() {
        return teams.size();
    }
}
