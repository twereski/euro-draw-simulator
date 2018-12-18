package com.example.draw.domain;

import java.util.Collection;

public interface PotRepository {
    Collection<Pot> findAll();
    Pot save(Pot pot);
    Pot get(int id);
}
