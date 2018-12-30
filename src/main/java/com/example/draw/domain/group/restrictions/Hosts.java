package com.example.draw.domain.group.restrictions;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

import java.util.List;

public class Hosts implements Restriction {

    private static final int MAX_IN_GROUP = 2;
    private final List<Team> hosts;

    public Hosts(List<Team> hosts) {
        this.hosts = hosts;
    }

    @Override
    public boolean isProhibited(Group group, Team team) {
        return Restriction.potentialGroupMax(group, team, hosts, MAX_IN_GROUP);
    }

}
