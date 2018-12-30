package com.example.draw.domain.group;

import com.example.draw.domain.DomainException;

import java.util.List;

class Groups {

    private final List<Group> groups;
    private final int minCapacity;

    public Groups(List<Group> groups) {
        this.groups = groups;
        this.minCapacity = groups.stream().mapToInt(Group::getCapacity).min().orElse(0);
        sort();
    }

    Group defaultGroup() {
        sort();
        return firstNotFull();
    }

    Group getNext(Group group) {
        int i = groups.indexOf(group) + 1;
        if (groups.size() == i) {
            return firstNotFull();
        }
        return groups.get(i);
    }

    int size() {
        return groups.size();
    }

    private Group firstNotFull() {
        return groups.stream().filter(Group::hasFreePlaces).findFirst()
                .orElseThrow(() -> new DomainException("There is not full group"));
    }

    private void sort() {
        this.groups.sort((g1, g2) -> {
            if (minCapacity - g1.size() == minCapacity - g2.size()) {
                return g1.getName().compareTo(g2.getName());
            } else {
                return Integer.compare(g2.freePlaces(), g1.freePlaces());
            }
        });
    }

}
