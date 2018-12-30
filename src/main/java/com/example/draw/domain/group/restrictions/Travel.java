package com.example.draw.domain.group.restrictions;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

import java.util.List;

public class Travel implements Restriction {
    private static final int MAX_IN_GROUP = 2;
    private final List<TeamTooFar> tooFar;

    public Travel(List<TeamTooFar> tooFar) {
        this.tooFar = tooFar;
    }

    public boolean isProhibited(Group group, Team team) {
        return tooFar.stream().anyMatch(t ->
                t.isFar(team) && t.commonTeams(group.getTeams()) > MAX_IN_GROUP
        );
    }

}
