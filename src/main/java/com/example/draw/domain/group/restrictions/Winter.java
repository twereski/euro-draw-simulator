package com.example.draw.domain.group.restrictions;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

import java.util.List;

public class Winter implements Restriction {

    private static final int MAX_IN_GROUP = 2;
    private final List<Team> winterTeams;

    public Winter(List<Team> winterTeams) {
        this.winterTeams = winterTeams;
    }

    public boolean isProhibited(Group group, Team team) {
        return Restriction.potentialGroupMax(group, team, winterTeams, MAX_IN_GROUP);
    }

}
