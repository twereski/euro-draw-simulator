package com.example.draw.domain;

import com.example.draw.domain.group.Group;
import com.example.draw.domain.pot.Pot;

import java.util.List;
import java.util.Queue;

class DrawScenario {

    private List<Group> groups;
    private Queue<Pot> pots;
    private int currentPot = 0;

    public DrawScenario(List<Group> groups, Queue<Pot> pots) {
        this.groups = groups;
        this.pots = pots;
    }
}
