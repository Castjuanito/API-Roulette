package com.roulette.Roulette.service;


import com.roulette.Roulette.model.Roulette;

import java.util.List;
import java.util.Optional;

public interface RouletteService {
    String newRoulette();

    Optional<Roulette> getRouletteById(String id);

    List<Roulette> getAllRoulettes();

    Boolean open(String id);

    Boolean close(String id);
}
