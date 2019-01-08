package com.example.draw.domain.group.restrictions;


import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

import java.util.Set;

public class ProhibitedTeams implements Restriction {

    private final Set<TeamPair> pairs;

    public ProhibitedTeams(Set<TeamPair> pairs) {
        this.pairs = pairs;
    }

    public boolean isProhibited(Group group, Team team) {
        return group.getTeams().stream()
                .anyMatch(t -> pairs.contains(new TeamPair(t, team)));
    }
}
