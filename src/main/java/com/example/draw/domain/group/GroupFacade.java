package com.example.draw.domain.group;

import com.example.draw.domain.DomainException;
import com.example.draw.domain.Team;
import com.example.draw.domain.group.restrictions.Restriction;

import java.util.List;

public class GroupFacade {

    private final GroupRepository repository;
    private Groups groups;
    private List<Restriction> restrictions;

    public GroupFacade(GroupRepository repository) {
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
        int attempt = 0;
        while (!tryAddTeamToGroup(team, group)) {
            if (attempt > groups.size()) {
                throw new DomainException("Cannot add team to any group");
            }
            group = groups.getNext(group);
            attempt++;
        }
    }

    private boolean tryAddTeamToGroup(Team team, Group group) {
        if (!group.hasFreePlaces()) {
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
