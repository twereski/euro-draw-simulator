package com.example.draw.domain

import spock.lang.Specification

class PotTest extends Specification {
    def "Draw"() {
        given:
        def team1 = new Team('xd')
        def team2 = new Team('xx')
        def teams = [team1, team2]
        def pot = new Pot(0, teams)
        when:
        def team = pot.draw()
        then:
        teams.contains(team)
    }
}
