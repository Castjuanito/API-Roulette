package com.roulette.Roulette.service;

import com.roulette.Roulette.model.Player;
import com.roulette.Roulette.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerServiceImplementation implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public String newPlayer(float credit) {
        UUID uuid = UUID.randomUUID();
        Player player = new Player(uuid.toString(), credit);
        playerRepository.save(player);
        return uuid.toString();
    }

    @Override
    public Optional<Player> getPlayerById(String id) {
        Optional<Player> player = playerRepository.findById(id);
        return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        Iterable<Player> players = playerRepository.findAll();
        return (List<Player>) players;
    }

    @Override
    public Boolean subtractCredit(Player player, float betAmount) {
        float newCredit = player.getCredit() - betAmount;
        if (newCredit >= 0) {
            player.setCredit(newCredit);
            playerRepository.save(player);
            return true;
        }
        return false;
    }
}
