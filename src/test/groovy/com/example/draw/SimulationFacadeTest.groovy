package com.example.draw

import com.example.draw.domain.Team
import com.example.draw.domain.group.Group
import com.example.draw.domain.group.restrictions.*
import com.example.draw.domain.pot.Pot
import com.example.draw.infrastracture.ConfigurationInMemory
import javafx.util.Pair
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
        and: "prapare facade"
        def facade = configuration.prepareSimulation()
        facade.prepareSimulation(pots, groups)
        facade.setRestrictions([prohibited, winter, travel, hosts])
        when: "the simulation is running according to procedure"
        facade.run()
        then: "every group is full (according group capacity)"
        configuration.getGroupRepository().findAll().each { assert it.freePlaces() == 0 }
        and: "pots are empty"
        configuration.getPotRepository().findAll().each { assert it.teams().isEmpty() }

        and: "teams from pot 0 are drawn into the group A-D"
        def teams0 = ['Switzerland', 'Portugal', 'Netherlands', 'England']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def groupA = configuration.getGroupRepository().get('A' as char)
        !Collections.disjoint(groupA.getTeams(), teams0)
        def groupC = configuration.getGroupRepository().get('C' as char)
        !Collections.disjoint(groupC.getTeams(), teams0)

        and: "teams from pot 6 are drawn into the group F-J"
        def teams6 = ['Latvia', 'Liechtenstein', 'Andorra', 'Malta', 'San Marino']
                .stream().map() { s -> new Team(s) }.collect(Collectors.toList())
        def groupF = configuration.getGroupRepository().get('F' as char)
        !Collections.disjoint(groupF.getTeams(), teams6)
        def groupH = configuration.getGroupRepository().get('H' as char)
        !Collections.disjoint(groupH.getTeams(), teams6)

        and: "winter venue restrictions role has been fulfilled"
        and: "excessive travel restriction role has been fulfilled"
    }

    def setUpPots() {
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

        return [pot0, pot1, pot2, pot3, pot4, pot5, pot6]
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
        names.each { k, v -> pairs.add(new Pair<>(new Team(k), new Team(v))) }
        return new ProhibitedTeams(pairs)
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
