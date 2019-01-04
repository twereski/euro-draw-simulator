package com.example.draw.domain.group;

import java.util.List;
import java.util.stream.Stream;

class Groups {

    private final List<Group> groups;

    public Groups(List<Group> groups) {
        this.groups = groups;
        sort();
    }

    Stream<Group> groupStream() {
        sort();
        return groups.stream();
    }

    private void sort() {
        this.groups.sort((g1, g2) -> {
            if (g1.size() == g2.size()) {
                return g1.getName().compareTo(g2.getName());
            } else {
                return Integer.compare(g2.freePlaces(), g1.freePlaces());
            }
        });
    }

}
