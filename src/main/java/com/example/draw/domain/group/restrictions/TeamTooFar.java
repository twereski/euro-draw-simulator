package com.example.draw.domain.group.restrictions;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TeamTooFar {

    private static final int MAX_IN_GROUP = 2;

    private final Team team;
    private final List<Team> toFarTeams;

    public TeamTooFar(Team team, List<Team> toFarTeams) {
        this.team = team;
        this.toFarTeams = toFarTeams;
    }

    static Predicate<TeamTooFar> isFar(Team team) {
        return p -> p.team.equals(team);
    }

    static Predicate<TeamTooFar> hasCommonTeams(Group group) {
        return p -> {
            List<Team> copyOf = new ArrayList<>(group.getTeams());
            copyOf.retainAll(p.toFarTeams);
            return copyOf.size() > MAX_IN_GROUP;
        };
    }
}
