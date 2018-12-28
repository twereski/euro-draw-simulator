package com.example.draw.domain.pot;

import java.util.List;

public interface PotRepository {
    List<Pot> findAll();
    Pot save(Pot pot);
    Pot get(int id);
}
