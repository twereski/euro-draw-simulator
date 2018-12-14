package com.example.draw.domain

import spock.lang.Specification

class DrawScenarioTest extends Specification {
    def "should start draw from the first pot"() {
        given:
        def groupA = Mock(Group)
        def groupB = Mock(Group)
        def pot = Mock(Pot)
        def scenario = new DrawScenario([groupA, groupB], [pot] as Queue<Pot>)
//        when:
//        def team = scenario.draw()
//        then:
//        !pot.teams().contains(team)
    }

}
