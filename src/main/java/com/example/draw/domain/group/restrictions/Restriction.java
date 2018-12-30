package com.example.draw.domain.group.restrictions;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

import java.util.ArrayList;
import java.util.List;

public interface Restriction {
    boolean isProhibited(Group group, Team team);

    static boolean potentialGroupMax(Group group, Team team, List<Team> prohibited, int maxInGroup) {
        if (!prohibited.contains(team)) {
            return false;
        }

        List<Team> potentialGroup = new ArrayList<>(group.getTeams());
        potentialGroup.add(team);
        potentialGroup.retainAll(prohibited);
        return potentialGroup.size() > maxInGroup;
    }

}
