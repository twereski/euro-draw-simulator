package com.example.draw.infrastracture;

import com.example.draw.SimulationFacade;
import com.example.draw.domain.GroupManager;
import com.example.draw.domain.PotsManager;
import lombok.Getter;

public class ConfigurationInMemory {

    @Getter
    private final InMemoryGroupRepository groupRepository = new InMemoryGroupRepository();
    @Getter
    private final InMemoryPotRepository potRepository = new InMemoryPotRepository();

    public SimulationFacade prepareSimulation() {
        return new SimulationFacade(preparePots(), prepareGroups());
    }

    private GroupManager prepareGroups() {
        return new GroupManager(groupRepository);
    }

    private PotsManager preparePots() {
        return new PotsManager(potRepository);
    }
}
