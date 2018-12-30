package com.example.draw;

import com.example.draw.domain.GroupManager;
import com.example.draw.domain.PotsManager;
import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;
import com.example.draw.domain.pot.Pot;
import com.example.draw.domain.restrictions.Restriction;

import java.util.List;

public class SimulationFacade {

    private PotsManager potsManager;
    private GroupManager groupManager;

    public SimulationFacade(PotsManager potsManager, GroupManager groupManager) {
        this.potsManager = potsManager;
        this.groupManager = groupManager;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        groupManager.setRestrictions(restrictions);
    }

    public void prepareSimulation(List<Pot> pots, List<Group> groups) {
        potsManager.setUpPots(pots);
        groupManager.setUpGroups(groups);
    }

    public void run() {
        while (!potsManager.empty()) {
            Team randomTeam = potsManager.getRandomFromCurrentPot();
            groupManager.addTeam(randomTeam);
        }
    }
}
