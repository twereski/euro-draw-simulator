package com.example.draw.domain;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Random;

public class Pot {

    private int id;
    private List<Team> teams;
    private Random random;

    public Pot(int id, List<Team> teams) {
        this.id = id;
        this.teams = teams;
        this.random = new Random();
    }

    public Team draw() {
        if(teams.isEmpty()) {
            throw new DomainException();
        }
        return teams.remove(random.nextInt(teams.size()));
    }

    public ImmutableList<Team> teams() {
        return ImmutableList.copyOf(teams);
    }
}
