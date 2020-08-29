package com.roulette.Roulette.repository;

import com.roulette.Roulette.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends CrudRepository<Bet, String> {
    List<Bet> findAllByRouletteId(String RouletteId);
}