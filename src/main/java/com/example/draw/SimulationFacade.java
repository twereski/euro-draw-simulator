package com.example.draw;

import com.example.draw.domain.GroupRepository;
import com.example.draw.domain.PotRepository;
import com.example.draw.domain.restrictions.ProhibitedTeams;

public class SimulationFacade {

    private PotRepository potRepository;
    private GroupRepository groupRepository;
    private ProhibitedTeams prohibitedTeams;

    public SimulationFacade(PotRepository potRepository, GroupRepository groupRepository, ProhibitedTeams prohibitedTeams) {
        this.potRepository = potRepository;
        this.groupRepository = groupRepository;
        this.prohibitedTeams = prohibitedTeams;
    }
}
