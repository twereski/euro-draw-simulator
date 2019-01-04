package com.example.draw;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;
import com.example.draw.domain.group.GroupFacade;
import com.example.draw.domain.group.restrictions.Restriction;
import com.example.draw.domain.pot.Pot;
import com.example.draw.domain.pot.PotsFacade;

import java.util.List;
import java.util.Set;

public class SimulationFacade {

    private final PotsFacade potsFacade;
    private final GroupFacade groupFacade;

    public SimulationFacade(PotsFacade potsFacade, GroupFacade groupFacade) {
        this.potsFacade = potsFacade;
        this.groupFacade = groupFacade;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        groupFacade.setRestrictions(restrictions);
    }

    public void prepareSimulation(Set<Pot> pots, List<Group> groups) {
        potsFacade.setUpPots(pots);
        groupFacade.setUpGroups(groups);
    }

    public void run() {
        while (!potsFacade.empty()) {
            Team randomTeam = potsFacade.getRandomFromCurrentPot();
            groupFacade.addTeam(randomTeam);
        }
    }
}
