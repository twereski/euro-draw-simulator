package com.example.draw.domain

import spock.lang.Specification

class SimulationTest extends Specification {
    def "Simulation of draws of euro elimination groups"() {
        given:"pots with teams according uefa ranking"
        and: "empty groups where team will be put after draw"
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

}
