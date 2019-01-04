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
        Group group = groups.groupStream()
                .filter(Group.hasFreePlace().and(Group.restrictionsDoNotBlock(team, restrictions)))
                .findFirst()
                .orElseThrow(() -> new DomainException("Cannot add team to any group - " + team.getName()));
        group.addTeam(team);
        repository.save(group);
    }
}
