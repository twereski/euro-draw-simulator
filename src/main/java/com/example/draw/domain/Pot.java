package com.example.draw.domain;

import java.util.List;
import java.util.Random;

public class Pot {

    private int id;
    private List<Team> teams;
    private Random random;

    public Pot(int id, List<Team> teams) {
        this.teams = teams;
        this.random = new Random();
    }

    public Team draw() {
        return teams.get(random.nextInt(teams.size()));
    }
}
