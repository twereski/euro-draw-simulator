package com.example.draw;

import com.example.draw.domain.GroupManager;
import com.example.draw.domain.PotsManager;
import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;
import com.example.draw.domain.group.GroupRepository;
import com.example.draw.domain.group.Groups;
import com.example.draw.domain.pot.Pot;
import com.example.draw.domain.pot.PotRepository;
import com.example.draw.domain.restrictions.Restriction;

import java.util.List;

public class SimulationFacade {

    private PotRepository potRepository;
    private GroupRepository groupRepository;
    private List<Restriction> restrictions;

    public SimulationFacade(PotRepository potRepository, GroupRepository groupRepository, List<Restriction> restrictions) {
        this.potRepository = potRepository;
        this.groupRepository = groupRepository;
        this.restrictions = restrictions;
    }

    public void run() {
        List<Pot> pots = potRepository.findAll();
        List<Group> groups = groupRepository.findAll();

        final PotsManager potsManager = new PotsManager(pots);
        final GroupManager groupManager = new GroupManager(new Groups(groups), restrictions);

        while (!potsManager.empty()) {
            Team randomTeam = potsManager.getRandomFromCurrentPot();
            groupManager.addTeam(randomTeam);
        }
        potRepository.saveAll(pots);
        groupRepository.saveAll(groups);
    }
}
