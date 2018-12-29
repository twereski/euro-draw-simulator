package com.example.draw

import com.example.draw.domain.Team
import com.example.draw.domain.group.Group
import com.example.draw.domain.group.GroupRepository
import com.example.draw.domain.pot.Pot
import com.example.draw.domain.restrictions.ProhibitedTeams
import com.example.draw.domain.restrictions.TeamTooFar
import com.example.draw.domain.restrictions.Travel
import com.example.draw.domain.restrictions.Winter
import com.example.draw.infrastracture.InMemoryGroupRepository
import com.example.draw.infrastracture.InMemoryPotRepository
import javafx.util.Pair
import spock.lang.Specification

import java.util.stream.Collectors

class SimulationFacadeTest extends Specification {
    InMemoryPotRepository potRepository = new InMemoryPotRepository()
    InMemoryGroupRepository groupRepository = new InMemoryGroupRepository()

    def "Simulation of draws of euro elimination groups"() {
        given: "pots with teams according uefa ranking"
        setUpPots(potRepository)
        and: "empty groups where team will be put after draw"
        setUpGroups(groupRepository)
        and: "prohibited team clashes are set"
        def prohibited = setUpProhibitedTeams()
        and: "winter venue restrictions teams are set"
        def winter = setUpWinter()
        and: "excessive travel restrictions are set"
        def travel = setUpTravel()
        def facade = new SimulationFacade(potRepository, groupRepository, [prohibited, winter, travel])
        when: "the simulation is running according to procedure"
        facade.run()
        then: "every group is full (according group capacity)"
        groupRepository.findAll().each { assert it.freePlaces() == 0 }
        and: "pots are empty"
        potRepository.findAll().each { assert it.teams().isEmpty() }
        and: "competition-related reasons role has been fulfilled"
        and: "prohibited team clashes role has been fulfilled"
        and: "winter venue restrictions role has been fulfilled"
        and: "excessive travel restriction role has been fulfilled"
    }

    def setUpPots(InMemoryPotRepository potRepository) {
        def teams0 = ['Switzerland', 'Portugal', 'Netherlands', 'England']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def teams1 = ['Belgium', 'France', 'Spain', 'Italy', 'Croatia', 'Poland']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def teams2 = ['Germany', 'Iceland', 'Bosnia-Herzegovina', 'Ukraine', 'Denmark', 'Sweden', 'Russia', 'Austria', 'Wales', 'Czech Republic']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def teams3 = ['Slovakia', 'Turkey', 'Republic of Ireland', 'Northern Ireland', 'Scotland', 'Norway', 'Serbia', 'Finland', 'Bulgaria', 'Israel']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def teams4 = ['Hungary', 'Romania', 'Greece', 'Albania', 'Montenegro', 'Cyprus', 'Estonia', 'Slovenia', 'Lithuania', 'Georgia']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def teams5 = ['FYR Macedonia', 'Kosovo', 'Belarus', 'Luxembourg', 'Armenia', 'Azerbaijan', 'Kazakhstan', 'Moldova', 'Gibraltar', 'Faroe Islands']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def teams6 = ['Latvia', 'Liechtenstein', 'Andorra', 'Malta', 'San Marino']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())

        def pot0 = new Pot(0, teams0)
        def pot1 = new Pot(1, teams1)
        def pot2 = new Pot(2, teams2)
        def pot3 = new Pot(3, teams3)
        def pot4 = new Pot(4, teams4)
        def pot5 = new Pot(5, teams5)
        def pot6 = new Pot(6, teams6)

        def pots = [pot0, pot1, pot2, pot3, pot4, pot5, pot6]

        pots.each {
            p -> potRepository.save(p)
        }
    }

    def setUpGroups(GroupRepository groupRepository) {
        ['F': 6, 'A': 5, 'B': 5, 'C': 5, 'D': 5, 'E': 5, 'G': 6, 'H': 6, 'I': 6, 'J': 6]
                .entrySet().stream().map() { s -> new Group(new Character(s.getKey() as char), s.getValue()) }
                .map() { g -> groupRepository.save(g) }.collect(Collectors.toList())
    }

    def setUpProhibitedTeams() {
        def names = ['Armenia': 'Azerbaijan', 'Gibraltar': 'Spain', 'Kosovo': 'Bosnia-Herzegovina',
                     'Serbia' : 'Kosovo', 'Ukraine': 'Russia']
        def pairs = []
        names.each { k, v -> pairs.add(new Pair<>(new Team(k), new Team(v))) }
        return new ProhibitedTeams(pairs)
    }

    def setUpWinter() {
        def teams = ['Belarus', 'Estonia', 'Faroe Islands', 'Finland', 'Iceland', 'Latvia', 'Lithuania',
        'Norway', 'Russia', 'Ukraine'].stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        return new Winter(teams)
    }

    def setUpTravel() {
        def teams1 = ['Andorra', 'England', 'France', 'Faroe Islands', 'Gibraltar', 'Iceland',
        'Malta', 'Northern Ireland', 'Portugal', 'Republic of Ireland', 'Scotland', 'Spain', 'Wales']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def tooFar1 = new TeamTooFar(new Team('Kazakhstan'), teams1)
        def teams2 = ['Gibraltar', 'Iceland', 'Portugal']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def tooFar2 = new TeamTooFar(new Team('Azerbaijan'), teams2)
        def teams3 = ['Armenia', 'Cyprus', 'Georgia', 'Israel']
                .stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        def tooFar3 = new TeamTooFar(new Team('Iceland'), teams3)

        return new Travel([tooFar1, tooFar2, tooFar3])
    }
}
