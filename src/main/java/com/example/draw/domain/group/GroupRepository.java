package com.example.draw.domain.group;

import java.util.List;

public interface GroupRepository {
    List<Group> findAll();

    Group save(Group group);

    Group get(Character name);

    void saveAll(List<Group> groups);
}
