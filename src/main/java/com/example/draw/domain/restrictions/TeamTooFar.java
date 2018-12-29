package com.example.draw.domain.restrictions;

import com.example.draw.domain.Team;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class TeamTooFar {

    private final Team team;
    private final List<Team> toFarTeams;

    public TeamTooFar(Team team, List<Team> toFarTeams) {
        this.team = team;
        this.toFarTeams = toFarTeams;
    }

    boolean isFar(Team team) {
        return this.team.equals(team);
    }

    int commonTeams(ImmutableList<Team> teams) {
        List<Team> copyOf = new ArrayList<>(teams);
        copyOf.retainAll(toFarTeams);
        return copyOf.size();
    }
}
