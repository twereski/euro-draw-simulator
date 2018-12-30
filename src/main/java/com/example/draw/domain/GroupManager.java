package com.example.draw.domain;

import com.example.draw.domain.group.Group;
import com.example.draw.domain.group.GroupRepository;
import com.example.draw.domain.group.Groups;
import com.example.draw.domain.restrictions.Restriction;

import java.util.List;

public class GroupManager {

    private final GroupRepository repository;
    private Groups groups;
    private List<Restriction> restrictions;

    public GroupManager(GroupRepository repository) {
        this.repository = repository;
    }

    public void setUpGroups(List<Group> groups) {
        repository.saveAll(groups);
        this.groups = new Groups(groups);
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public void addTeam(Team team) {
        Group group = groups.defaultGroup();
        while (!tryAddTeamToGroup(team, group)) {
            group = groups.getNext(group);
        }
    }

    private boolean tryAddTeamToGroup(Team team, Group group) {
        if (group.freePlaces() < 1) {
            return false;
        }
        if (restrictions.stream().noneMatch(r -> r.isProhibited(group, team))) {
            group.addTeam(team);
            repository.save(group);
            return true;
        }
        return false;
    }
}
