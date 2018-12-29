package com.example.draw.domain.restrictions;

import com.example.draw.domain.Team;
import com.example.draw.domain.group.Group;

public interface Restriction {
    boolean isProhibited(Group group, Team team);
}
