package com.example.draw.domain

import spock.lang.Specification

class PotTest extends Specification {
    def "Draw"() {
        given:
        def pot = new Pot()
        when:
        pot.draw()
        then:
        1==1
    }
}
