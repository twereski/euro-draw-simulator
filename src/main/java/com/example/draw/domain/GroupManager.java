package com.example.draw.domain;

import com.example.draw.domain.group.Group;
import com.example.draw.domain.group.Groups;
import com.example.draw.domain.restrictions.Restriction;

import java.util.List;

public class GroupManager {

    private final Groups groups;
    private final List<Restriction> restrictions;

    public GroupManager(Groups groups, List<Restriction> restrictions) {
        this.groups = groups;
        this.restrictions = restrictions;
    }

    public void addTeam(Team team) {
        Group group = groups.defaultGroup();
        while (!tryAddTeamToGroup(team, group)) {
            group = groups.getNext(group);
        }
    }

    private boolean tryAddTeamToGroup(Team team, Group group) {
        if(group.freePlaces() < 1) {
            return false;
        }
        if(restrictions.stream().anyMatch(r -> !r.isProhibited(group,team))) {
            group.addTeam(team);
            return true;
        }
        return false;
    }
}
