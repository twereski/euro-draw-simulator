package com.example.draw.domain.restrictions;

import com.example.draw.domain.group.Group;
import com.example.draw.domain.Team;

import java.util.List;

public class Winter implements Restriction {

    private static final int MAX_IN_GROUP = 2;
    private List<Team> winterTeams;

    public Winter(List<Team> winterTeams) {
        this.winterTeams = winterTeams;
    }

    public boolean isProhibited(Group group, Team team) {
        if(!winterTeams.contains(team)) {
            return false;
        }
        List<Team> potentialGroup = group.getTeams();
        potentialGroup.add(team);
        potentialGroup.retainAll(winterTeams);
        return potentialGroup.size() > MAX_IN_GROUP;
    }

}
