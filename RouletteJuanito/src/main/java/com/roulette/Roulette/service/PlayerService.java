package com.roulette.Roulette.service;

import com.roulette.Roulette.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    String newPlayer(float credit);

    Optional<Player> getPlayerById(String id);

    List<Player> getAllPlayers();

    Boolean subtractCredit(Player player, float betAmount);

}
