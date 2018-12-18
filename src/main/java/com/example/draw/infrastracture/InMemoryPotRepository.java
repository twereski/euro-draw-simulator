package com.example.draw.infrastracture;

import com.example.draw.domain.Pot;
import com.example.draw.domain.PotRepository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPotRepository implements PotRepository {

    private ConcurrentHashMap<Integer, Pot> map = new ConcurrentHashMap<Integer, Pot>();

    public Pot save(Pot pot) {
        map.put(pot.getId(), pot);
        return pot;
    }

    public Collection<Pot> findAll() {
        return map.values();
    }

    public Pot get(int id) {
        return map.get(id);
    }
}
