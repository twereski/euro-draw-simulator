package com.example.draw.domain.group;

import com.example.draw.domain.group.Group;

import java.util.List;

public interface GroupRepository {
    List<Group> findAll();
    Group save(Group pot);
    Group get(Character name);
}
