package com.example.draw

import com.example.draw.domain.Team
import com.example.draw.domain.group.Group
import com.example.draw.domain.group.restrictions.*
import com.example.draw.domain.pot.Pot
import com.example.draw.infrastracture.ConfigurationInMemory
import spock.lang.Specification

import java.util.stream.Collectors

class SimulationFacadeTest extends Specification {
    ConfigurationInMemory configuration = new ConfigurationInMemory()

    def "Simulation of draws of euro elimination groups"() {
        given: "pots with teams according uefa ranking"
        def pots = setUpPots()
        and: "empty groups where team will be put after draw"
        def groups = setUpGroups()
        and: "prohibited team clashes are set"
        def prohibited = setUpProhibitedTeams()
        and: "hosts team clashes are set"
        def hosts = setUpHosts()
        and: "winter venue restrictions teams are set"
        def winter = setUpWinter()
        and: "excessive travel restrictions are set"
        def travel = setUpTravel()
        and: "set initial conditions"
        def facade = configuration.prepareSimulation()
        facade.prepareSimulation(pots, groups)
        facade.setRestrictions([prohibited, winter, travel, hosts])
        when: "the simulation is running according to procedure"
        facade.run()
        then: "every group should be full (according group capacity)"
        configuration.getGroupRepository().findAll().each { assert !it.hasFreePlaces() }
        and: "pots are empty"
        configuration.getPotRepository().findAll().each { assert it.isEmpty() }

        and: "teams from pot 0 should be drawn into the group A-D"
        def groupA = configuration.getGroupRepository().get('A' as char)
        def groupC = configuration.getGroupRepository().get('C' as char)
        !Collections.disjoint(groupA.getTeams(), teamsInPots().get(0))
        !Collections.disjoint(groupC.getTeams(), teamsInPots().get(0))

        and: "teams from pot 6 should be drawn into the group F-J"
        def groupF = configuration.getGroupRepository().get('F' as char)
        def groupH = configuration.getGroupRepository().get('H' as char)
        !Collections.disjoint(groupF.getTeams(), teamsInPots().get(6))
        !Collections.disjoint(groupH.getTeams(), teamsInPots().get(6))

        and: "teams from pot 1 should be drawn into the group E-J"
        def groupE = configuration.getGroupRepository().get('E' as char)
        def groupJ = configuration.getGroupRepository().get('J' as char)
        !Collections.disjoint(groupE.getTeams(), teamsInPots().get(1))
        !Collections.disjoint(groupJ.getTeams(), teamsInPots().get(1))

    }

    def teamsInPots() {
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

        def map = [0: teams0, 1: teams1, 2: teams2, 3: teams3, 4: teams4, 5: teams5, 6: teams6]

        return map
    }

    def setUpPots() {
        def map = teamsInPots()

        def pot0 = new Pot(0, map.get(0))
        def pot1 = new Pot(1, map.get(1))
        def pot2 = new Pot(2, map.get(2))
        def pot3 = new Pot(3, map.get(3))
        def pot4 = new Pot(4, map.get(4))
        def pot5 = new Pot(5, map.get(5))
        def pot6 = new Pot(6, map.get(6))

        return [pot0, pot1, pot2, pot3, pot4, pot5, pot6].toSet()
    }

    def setUpGroups() {
        return ['F': 6, 'A': 5, 'B': 5, 'C': 5, 'D': 5, 'E': 5, 'G': 6, 'H': 6, 'I': 6, 'J': 6]
                .entrySet().stream().map() { s -> new Group(new Character(s.getKey() as char), s.getValue()) }
                .collect(Collectors.toList())
    }

    def setUpProhibitedTeams() {
        def names = ['Armenia': 'Azerbaijan', 'Gibraltar': 'Spain', 'Kosovo': 'Bosnia-Herzegovina',
                     'Serbia' : 'Kosovo', 'Ukraine': 'Russia']
        def pairs = []
        names.each { k, v -> pairs.add(new TeamPair(new Team(k), new Team(v))) }
        return new ProhibitedTeams(pairs.toSet())
    }

    def setUpWinter() {
        def teams = ['Belarus', 'Estonia', 'Faroe Islands', 'Finland', 'Iceland', 'Latvia', 'Lithuania',
        'Norway', 'Russia', 'Ukraine'].stream().map() {s -> new Team(s)}.collect(Collectors.toList())
        return new Winter(teams)
    }

    def setUpHosts() {
        def teams = ['England', 'Germany', 'Italy', 'Russia', 'Romania', 'Azerbaijan', 'Netherlands',
                     'Republic of Ireland', 'Scotland', 'Hungary', 'Denmark', 'Spain']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        return new Hosts(teams)
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
