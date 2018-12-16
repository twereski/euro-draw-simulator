package com.example.draw.domain;

public class PotFacade {

    private PotRepository potRepository;

    public PotFacade(PotRepository potRepository) {
        this.potRepository = potRepository;
    }
}
