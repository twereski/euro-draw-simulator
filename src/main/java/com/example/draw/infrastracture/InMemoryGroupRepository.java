package com.example.draw.infrastracture;

import com.example.draw.domain.Group;
import com.example.draw.domain.GroupRepository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGroupRepository implements GroupRepository {

    private ConcurrentHashMap<String, Group> map = new ConcurrentHashMap<String, Group>();

    public Group save(Group group) {
        map.put(group.getName(), group);
        return group;
    }

    public Collection<Group> findAll() {
        return map.values();
    }

    public Group get(String name) {
        return map.get(name);
    }
}
