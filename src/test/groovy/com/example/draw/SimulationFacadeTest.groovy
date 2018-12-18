package com.example.draw

import com.example.draw.domain.Group
import com.example.draw.domain.GroupRepository
import com.example.draw.domain.Pot
import com.example.draw.domain.PotFacade
import com.example.draw.domain.Team
import com.example.draw.infrastracture.InMemoryGroupRepository
import com.example.draw.infrastracture.InMemoryPotRepository
import com.example.draw.infrastracture.PotConfiguration
import spock.lang.Specification

import java.util.stream.Collectors

class SimulationFacadeTest extends Specification {
        InMemoryPotRepository potRepository = new InMemoryPotRepository()
    InMemoryGroupRepository groupRepository = new InMemoryGroupRepository()
    PotFacade potFacade = new PotConfiguration().potFacade()

    def "Simulation of draws of euro elimination groups"() {
        given:"pots with teams according uefa ranking"
        setUpPots(potRepository)
        def pots = potRepository.findAll()
        and: "empty groups where team will be put after draw"
        setUpGroups(groupRepository)
        def groups = groupRepository.findAll()
        and: "prohibited team clashes are set"
        and: "winter venue restrictions teams are set"
        when: "the simulation run"

        then: "every group is full (according group capacity)"
        and: "pots are empty"
        and: "competition-related reasons role has been fulfilled"
        and: "prohibited team clashes role has been fulfilled"
        and: "winter venue restrictions role has been fulfilled"
        and: "excessive travel restriction role has been fulfilled"
    }

    def setUpPots(InMemoryPotRepository potRepository) {
        def teams0 = ['Switzerland','Portugal', 'Netherlands', 'England']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def teams1 = ['Belgium', 'France', 'Spain', 'Italy', 'Croatia', 'Poland']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def teams2 = ['Germany', 'Iceland', 'Bosnia and Herzegovina', 'Ukraine', 'Denmark', 'Sweden', 'Russia', 'Austria', 'Wales', 'Czech Republic']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def teams3 = ['Slovakia', 'Turkey', 'Republic of Ireland', 'Northern Ireland', 'Scotland', 'Norway', 'Serbia', 'Finland', 'Bulgaria', 'Israel']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def teams4 = ['Hungary', 'Romania', 'Greece', 'Albania', 'Montenegro', 'Cyprus', 'Estonia', 'Slovenia', 'Lithuania', 'Georgia']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def teams5 = ['FYR Macedonia', 'Kosovo', 'Belarus', 'Luxembourg', 'Armenia', 'Armenia', 'Azerbaijan', 'Kazakhstan', 'Moldova', 'Gibraltar', 'Faroe Islands']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def teams6 = ['Latvia', 'Liechtenstein', 'Andorra', 'Malta', 'San Marino']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())

        def pot0 = new Pot(0, teams0)
        def pot1 = new Pot(1, teams1)
        def pot2 = new Pot(2, teams2)
        def pot3 = new Pot(3, teams3)
        def pot4 = new Pot(4, teams4)
        def pot5 = new Pot(5, teams5)
        def pot6 = new Pot(6, teams6)

        def pots = [pot0, pot1, pot2, pot3, pot4, pot5, pot6]

        pots.each {
            p -> potRepository.save(p);
        }
    }

    def setUpGroups(GroupRepository groupRepository) {
        ['A', 'B', 'C', 'D', 'E', 'G', 'H', 'I', 'J']
                .stream().map() {s -> new Group(s)}.map() {g -> groupRepository.save(g)}.collect(Collectors.toList())
    }
}
