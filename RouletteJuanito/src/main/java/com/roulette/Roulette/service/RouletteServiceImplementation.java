package com.roulette.Roulette.service;

import com.roulette.Roulette.model.Roulette;
import com.roulette.Roulette.repository.RouletteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RouletteServiceImplementation implements RouletteService {

    @Autowired
    private RouletteRepository rouletteRepository;

    @Override
    public String newRoulette() {
        UUID uuid = UUID.randomUUID();
        Roulette roulette = new Roulette(uuid.toString(), false);
        rouletteRepository.save(roulette);
        return uuid.toString();
    }

    @Override
    public Optional<Roulette> getRouletteById(String id) {
        Optional<Roulette> roulette = rouletteRepository.findById(id);
        return roulette;
    }

    @Override
    public List<Roulette> getAllRoulettes() {
        Iterable<Roulette> roulettes = rouletteRepository.findAll();
        return (List<Roulette>) roulettes;
    }

    @Override
    public Boolean open(String id) {
        Optional<Roulette> roulette = rouletteRepository.findById(id);
        if (roulette.isPresent()) {
            Roulette roulet = roulette.get();
            if (!roulet.getIsOpen()) {
                roulet.setIsOpen(true);
                rouletteRepository.save(roulet);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean close(String id) {
        Optional<Roulette> roulette = rouletteRepository.findById(id);
        if (roulette.isPresent()) {
            Roulette roulet = roulette.get();
            if (roulet.getIsOpen()) {
                roulet.setIsOpen(false);
                rouletteRepository.save(roulet);
            }
            return true;
        }
        return false;
    }
}
