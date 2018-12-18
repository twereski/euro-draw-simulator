package com.example.draw.domain;

import java.util.Collection;

public interface GroupRepository {
    Collection<Group> findAll();
    Group save(Group pot);
    Group get(String name);
}
