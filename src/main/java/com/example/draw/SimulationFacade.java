package com.example.draw;

import com.example.draw.domain.*;
import com.example.draw.domain.restrictions.ProhibitedTeams;

import java.util.List;

public class SimulationFacade {

    private PotRepository potRepository;
    private GroupRepository groupRepository;
    private ProhibitedTeams prohibitedTeams;

    public SimulationFacade(PotRepository potRepository, GroupRepository groupRepository, ProhibitedTeams prohibitedTeams) {
        this.potRepository = potRepository;
        this.groupRepository = groupRepository;
        this.prohibitedTeams = prohibitedTeams;
    }

    public void run() {
        List<Pot> pots = potRepository.findAll();
        final PotsManager manager = new PotsManager(pots);
        while (manager.empty()) {
            Team randomTeam = manager.getRandomFromCurrentPot();
        }
    }
}
