package com.example.draw;

import com.example.draw.domain.GroupRepository;
import com.example.draw.domain.PotRepository;

public class SimulationFacade {

    private PotRepository potRepository;
    private GroupRepository groupRepository;

    public SimulationFacade(PotRepository potRepository, GroupRepository groupRepository) {
        this.potRepository = potRepository;
        this.groupRepository = groupRepository;
    }
}
