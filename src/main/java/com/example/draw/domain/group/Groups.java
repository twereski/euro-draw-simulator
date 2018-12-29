package com.example.draw.domain.group;

import com.example.draw.domain.DomainException;
import java.util.List;

public class Groups {

    private final List<Group> groups;
    private final int minCapacity;

    public Groups(List<Group> groups) {
        this.groups = groups;
        this.minCapacity = groups.stream().mapToInt(Group::getCapacity).min().orElse(0);
        sort();
    }

    public Group defaultGroup()
    {
        sort();
        return  firstNotFull();
    }

    public Group getNext(Group group)
    {
        int i = groups.indexOf(group)+1;
        if(groups.size() == i) {
            return firstNotFull();
        }
        return  groups.get(i);
    }

    private Group firstNotFull() {
        return groups.stream().filter(g -> g.freePlaces() > 0).findFirst()
                .orElseThrow(DomainException::new);
    }

    private void sort() {
        this.groups.sort((g1, g2) -> {
           if( minCapacity - g1.size() == minCapacity - g2.size()) {
                return g1.getName().compareTo(g2.getName());
           } else {
                return g2.freePlaces() - g1.freePlaces();
           }
        });
    }

}
