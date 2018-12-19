package com.example.draw.domain.restrictions;

import com.example.draw.domain.Group;
import com.example.draw.domain.Team;

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
