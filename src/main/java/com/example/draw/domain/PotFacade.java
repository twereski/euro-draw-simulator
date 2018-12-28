package com.example.draw.domain;

import com.example.draw.domain.pot.PotRepository;

public class PotFacade {

    private PotRepository potRepository;

    public PotFacade(PotRepository potRepository) {
        this.potRepository = potRepository;
    }
}
