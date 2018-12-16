package com.example.draw.infrastracture;

import com.example.draw.domain.PotFacade;
import com.example.draw.domain.PotRepository;

public class PotConfiguration {

    public PotFacade potFacade() {
        return potFacade(new InMemoryPotRepository());
    }

    private PotFacade potFacade(PotRepository potRepository) {
        return new PotFacade(potRepository);
    }
}
