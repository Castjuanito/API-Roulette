package com.roulette.Roulette.service;

import com.roulette.Roulette.model.Bet;
import com.roulette.Roulette.model.Player;
import com.roulette.Roulette.model.Roulette;

import java.util.List;


public interface BetService {

    Boolean newBet(Roulette roulette, Player player, float amount, int number, Bet.ColorEnum color);

    List<Bet> closeBets(String rouletteId);

}
