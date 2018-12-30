package com.example.draw.domain;

import com.example.draw.domain.pot.Pot;
import com.example.draw.domain.pot.PotRepository;

import java.util.List;

public class PotsManager {

    private final PotRepository repository;

    public PotsManager(PotRepository repository) {
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
