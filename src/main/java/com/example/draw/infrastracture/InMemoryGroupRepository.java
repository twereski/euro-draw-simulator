package com.example.draw.infrastracture;

import com.example.draw.domain.group.Group;
import com.example.draw.domain.group.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGroupRepository implements GroupRepository {

    private ConcurrentHashMap<Character, Group> map = new ConcurrentHashMap<>();

    public Group save(Group group) {
        map.put(group.getName(), group);
        return group;
    }

    public List<Group> findAll() {
        return new ArrayList<>(map.values());
    }

    public Group get(Character name) {
        return map.get(name);
    }

    @Override
    public void saveAll(List<Group> groups) {
        groups.forEach(g -> map.put(g.getName(), g));
    }
}
