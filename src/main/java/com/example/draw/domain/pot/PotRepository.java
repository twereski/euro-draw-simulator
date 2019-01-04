package com.example.draw.domain.pot;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PotRepository {
    List<Pot> findAll();

    Optional<Pot> getFirstNotEmpty();

    Pot save(Pot pot);

    void saveAll(Collection<Pot> pots);

    Pot get(int id);
}
