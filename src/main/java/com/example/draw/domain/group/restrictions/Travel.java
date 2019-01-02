package com.example.draw.domain.group.restrictions;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

import java.util.List;

public class Travel implements Restriction {
    private final List<TeamTooFar> tooFar;

    public Travel(List<TeamTooFar> tooFar) {
        this.tooFar = tooFar;
    }

    public boolean isProhibited(Group group, Team team) {
        return tooFar.stream().anyMatch(
                TeamTooFar.isFar(team).and(TeamTooFar.hasCommonTeams(group))
        );
    }
}
