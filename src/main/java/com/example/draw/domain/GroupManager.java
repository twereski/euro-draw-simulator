package com.example.draw.domain;

import com.example.draw.domain.restrictions.Restriction;

import java.util.List;

public class GroupManager {

    private final List<Group> groups;
    private final List<Restriction> restrictions;

    public GroupManager(List<Group> groups, List<Restriction> restrictions) {
        this.groups = groups;
        this.restrictions = restrictions;
    }

    public void addTeam(Team team) {
        Group group = correspondingGroup();
        if(restrictions.stream().anyMatch(r -> r.isProhibited(group,team))) {
//            group = getNext(group);
        }
        group.addTeam(team);
    }

    private Group correspondingGroup()
    {
        return  groups.get(0);
    }
    private Group getNext(Group group)
    {
        return  groups.get(0);
    }
}
