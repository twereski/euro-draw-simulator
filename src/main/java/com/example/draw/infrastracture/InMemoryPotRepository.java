package com.example.draw.infrastracture;

import com.example.draw.domain.Pot;
import com.example.draw.domain.PotRepository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPotRepository implements PotRepository {

    private ConcurrentHashMap<Integer, Pot> map = new ConcurrentHashMap<Integer, Pot>();

    Pot save(Pot pot) {
        map.put(pot.getId(), pot);
        return pot;
    }

    Collection<Pot> findAll() {
        return map.values();
    }

    Pot get(int id) {
        return map.get(id);
    }
}
