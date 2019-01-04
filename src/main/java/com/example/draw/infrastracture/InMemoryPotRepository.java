package com.example.draw.infrastracture;

import com.example.draw.domain.pot.Pot;
import com.example.draw.domain.pot.PotRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPotRepository implements PotRepository {

    private final ConcurrentHashMap<Integer, Pot> map = new ConcurrentHashMap<>();

    public Pot save(Pot pot) {
        map.put(pot.getId(), pot);
        return pot;
    }

    @Override
    public void saveAll(Collection<Pot> pots) {
        pots.forEach(p -> map.put(p.getId(), p));
    }

    public List<Pot> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Pot> getFirstNotEmpty() {
        return map.entrySet().stream().filter(e -> !e.getValue().isEmpty()).map(Map.Entry::getValue).findFirst();
    }

    public Pot get(int id) {
        return map.get(id);
    }
}
