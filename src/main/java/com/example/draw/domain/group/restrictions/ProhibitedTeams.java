package com.example.draw.domain.group.restrictions;


import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;
import javafx.util.Pair;

import java.util.Set;

public class ProhibitedTeams implements Restriction {

    private final Set<Pair<Team, Team>> pairs;

    public ProhibitedTeams(Set<Pair<Team, Team>> pairs) {
        this.pairs = pairs;
    }

    public boolean isProhibited(Group group, Team team) {
        return group.getTeams().stream()
                .anyMatch(t -> pairs.contains(new Pair<>(team, t)) || pairs.contains(new Pair<>(t, team)));
    }
}
