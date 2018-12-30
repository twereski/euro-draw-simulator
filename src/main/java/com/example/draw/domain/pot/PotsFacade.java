package com.example.draw.domain.pot;

import com.example.draw.domain.DomainException;
import com.example.draw.domain.Team;

import java.util.List;

public class PotsFacade {

    private final PotRepository repository;

    public PotsFacade(PotRepository repository) {
        this.repository = repository;

    }

    public void setUpPots(List<Pot> pots) {
        repository.saveAll(pots);
    }

    public boolean empty() {
        return this.repository.findAll().stream().allMatch(p -> p.teams().isEmpty());
    }

    public Team getRandomFromCurrentPot() {
        Pot currentPot = repository.getFirstNotEmpty().orElseThrow(DomainException::new);
        Team team = currentPot.draw();
        repository.save(currentPot);
        return team;
    }
}
