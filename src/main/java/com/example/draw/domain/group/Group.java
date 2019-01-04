package com.example.draw.domain.group;

import com.example.draw.domain.DomainException;
import com.example.draw.domain.Team;
import com.example.draw.domain.group.restrictions.Restriction;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@EqualsAndHashCode(of = {"name"})
public class Group {

    @Getter
    private final Character name;
    @Getter
    private final int capacity;

    private final List<Team> teams = new ArrayList<>();

    public Group(Character name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    void addTeam(Team team) {
        if (!hasFreePlaces()) {
            throw new DomainException("Cannot add team, group is full");
        }
        teams.add(team);
    }

    public ImmutableList<Team> getTeams() {
        return ImmutableList.copyOf(teams);
    }

    public boolean hasFreePlaces() {
        return freePlaces() >= 1;
    }

    int freePlaces() {
        return capacity - teams.size();
    }

    int size() {
        return teams.size();
    }

    static Predicate<Group> hasFreePlace() {
        return Group::hasFreePlaces;
    }

    static Predicate<Group> restrictionsDoNotBlock(Team team, List<Restriction> restrictions) {
        return p -> restrictions.stream().noneMatch(r -> r.isProhibited(p, team));
    }
}
