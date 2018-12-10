package com.example.draw.domain;

import java.util.List;
import java.util.Queue;

public class DrawScenario {

    private List<Group> groups;
    private Queue<Pot> pots;

    public DrawScenario(List<Group> groups, Queue<Pot> pots) {
        this.groups = groups;
        this.pots = pots;
    }
}
