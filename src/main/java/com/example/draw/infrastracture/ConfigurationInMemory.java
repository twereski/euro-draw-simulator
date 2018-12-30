package com.example.draw.infrastracture;

import com.example.draw.SimulationFacade;
import com.example.draw.domain.group.GroupFacade;
import com.example.draw.domain.pot.PotsFacade;
import lombok.Getter;

public class ConfigurationInMemory {

    @Getter
    private final InMemoryGroupRepository groupRepository = new InMemoryGroupRepository();
    @Getter
    private final InMemoryPotRepository potRepository = new InMemoryPotRepository();

    public SimulationFacade prepareSimulation() {
        return new SimulationFacade(preparePots(), prepareGroups());
    }

    private GroupFacade prepareGroups() {
        return new GroupFacade(groupRepository);
    }

    private PotsFacade preparePots() {
        return new PotsFacade(potRepository);
    }
}
