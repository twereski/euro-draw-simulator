package com.example.draw.domain.pot;

import com.example.draw.domain.DomainException;
import com.example.draw.domain.Team;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@EqualsAndHashCode(of = {"id"})
public class Pot {

    @Getter
    private int id;
    private List<Team> teams;
    private Random random;

    public Pot(int id, List<Team> teams) {
        this.id = id;
        this.teams = teams;
        this.random = new Random();
    }

    public Team draw() {
        if (teams.isEmpty()) {
            throw new DomainException();
        }
        return teams.remove(random.nextInt(teams.size()));
    }

    public ImmutableList<Team> teams() {
        return ImmutableList.copyOf(teams);
    }
}
