package com.example.draw.domain.group;

import java.util.Comparator;
import java.util.List;

public class Groups {

    private final List<Group> groups;

    public Groups(List<Group> groups) {
        this.groups = groups;
        sort();
    }

    public Group defaultGroup()
    {
        sort();
        return  groups.get(0);
    }

    public Group getNext(Group group)
    {
        int i = groups.indexOf(group);
        return  groups.get(i+1);
    }

    private void sort() {
        this.groups.sort((g1, g2) -> {
           if(g1.freePlaces() == g2.freePlaces()) {
                return g1.getName().compareTo(g2.getName());
           } else {
                return g2.freePlaces() - g1.freePlaces();
           }
        });
    }

}
