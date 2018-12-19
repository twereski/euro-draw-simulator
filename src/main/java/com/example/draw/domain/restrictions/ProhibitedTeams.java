package com.example.draw.domain.restrictions;


import com.example.draw.domain.Group;
import com.example.draw.domain.Team;
import javafx.util.Pair;

import java.util.List;

public class ProhibitedTeams implements Restriction {

    private final List<Pair<Team, Team>> pairs;

    public ProhibitedTeams(List<Pair<Team, Team>> pairs) {
        this.pairs = pairs;
    }

    public boolean isProhibited(Group group, Team team) {
        return group.getTeams().stream()
                .anyMatch(t -> pairs.contains(new Pair<>(team, t)) || pairs.contains(new Pair<>(t, team)));
    }
}
