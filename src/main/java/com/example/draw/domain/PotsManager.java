package com.example.draw.domain;

import java.util.List;

public class PotsManager {

    private final List<Pot> pots;
    private Pot currentPot;

    public PotsManager(List<Pot> pots) {
        if(pots.isEmpty()) {
            throw new DomainException();
        }
        this.pots = pots;
        this.currentPot = pots.get(0);
    }

    public boolean empty() {
        return this.pots.stream().allMatch(p -> p.teams().isEmpty());
    }

    public Team getRandomFromCurrentPot() {
        if(currentPot.teams().isEmpty()) {
            if(!pots.iterator().hasNext()) {
                throw new DomainException();
            }
            currentPot = pots.iterator().next();
        }
        return currentPot.draw();
    }
}
