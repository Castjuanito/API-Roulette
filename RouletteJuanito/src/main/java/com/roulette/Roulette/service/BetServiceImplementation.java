package com.roulette.Roulette.service;

import com.roulette.Roulette.model.Bet;
import com.roulette.Roulette.model.Player;
import com.roulette.Roulette.model.Roulette;
import com.roulette.Roulette.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BetServiceImplementation implements BetService {
    @Autowired
    private BetRepository betRepository;

    @Override
    public Boolean newBet(Roulette roulette, Player player, float amount, int number, Bet.ColorEnum color) {
        if (player.getCredit() >= amount && roulette.getIsOpen() == true) {
            UUID uuid = UUID.randomUUID();
            Bet bet = new Bet(uuid.toString(), number, color, amount, false, player.getPlayerId(), roulette.getRouletteId());
            betRepository.save(bet);
            return true;
        }
        return false;
    }

    @Override
    public List<Bet> closeBets(String rouletteId) {
        Random rand = new Random();
        int number = rand.nextInt(37);
        List<Bet> bets = new ArrayList<>();
        Bet.ColorEnum color = Bet.ColorEnum.values()[rand.nextInt(Bet.ColorEnum.values().length)];

        betRepository.findAllByRouletteId(rouletteId).forEach(
                bet -> {
                    bet.setIsWinner(bet.getColor() == color || bet.getNumber() == number);
                    betRepository.save(bet);
                    bets.add(bet);
                }
        );
        return bets;
    }
}
