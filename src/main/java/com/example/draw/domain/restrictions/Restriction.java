package com.example.draw.domain.restrictions;

import com.example.draw.domain.Group;
import com.example.draw.domain.Team;

public interface Restriction {
    boolean isProhibited(Group group, Team team);
}