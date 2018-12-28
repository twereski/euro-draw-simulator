package com.example.draw.infrastracture;

import com.example.draw.domain.pot.Pot;
import com.example.draw.domain.pot.PotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPotRepository implements PotRepository {

    private ConcurrentHashMap<Integer, Pot> map = new ConcurrentHashMap<>();

    public Pot save(Pot pot) {
        map.put(pot.getId(), pot);
        return pot;
    }

    public List<Pot> findAll() {
        return new ArrayList<>(map.values());
    }

    public Pot get(int id) {
        return map.get(id);
    }
}
