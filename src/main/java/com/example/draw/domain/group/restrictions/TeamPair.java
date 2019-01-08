package com.example.draw.domain.group.restrictions;

import com.example.draw.domain.Team;


public class TeamPair {

    private final Team teamOne;
    private final Team teamTwo;

    public TeamPair(Team teamOne, Team teamTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof TeamPair)) return false;
        TeamPair other = (TeamPair) o;

        if (teamOne.equals(other.teamOne) && teamTwo.equals(other.teamTwo)) {
            return true;
        }
        if (teamOne.equals(other.teamTwo) && teamTwo.equals(other.teamOne)) {
            return true;
        }

        return true;
    }
}
