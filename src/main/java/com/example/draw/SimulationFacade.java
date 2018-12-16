package com.example.draw;

import com.example.draw.domain.PotRepository;

public class SimulationFacade {

    private PotRepository potRepository;

    public SimulationFacade(PotRepository potRepository) {
        this.potRepository = potRepository;
    }
}
