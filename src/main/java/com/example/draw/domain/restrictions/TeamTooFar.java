package com.example.draw.domain.restrictions;

import com.example.draw.domain.Group;
import com.example.draw.domain.Team;

import java.util.List;

public class TeamTooFar {

    private final Team team;
    private final List<Team> toFarTeams;

    public TeamTooFar(Team team, List<Team> toFarTeams) {
        this.team = team;
        this.toFarTeams = toFarTeams;
    }

    public boolean isFar(Team team) {
        return this.team.equals(team);
    }

    public int commonTeams(List<Team> teams) {
        teams.retainAll(toFarTeams);
        return  teams.size();
    }
}
