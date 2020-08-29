package com.roulette.Roulette.api;

import com.roulette.Roulette.Utils.EnumUtils;
import com.roulette.Roulette.api.requestBodies.BetRequest;
import com.roulette.Roulette.model.Bet;
import com.roulette.Roulette.model.Player;
import com.roulette.Roulette.model.Roulette;
import com.roulette.Roulette.service.BetService;
import com.roulette.Roulette.service.PlayerService;
import com.roulette.Roulette.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bet")
public class BetRestController {

    @Autowired
    BetService betService;
    @Autowired
    PlayerService playerService;
    @Autowired
    RouletteService rouletteService;

    public static boolean isValidBetRequest(BetRequest betRequest) {
        return betRequest.getRouletteId() != null &&
                betRequest.getAmount() != null &&
                (betRequest.getColor() != null || betRequest.getNumber() != null);
    }

    @PostMapping("")
    public ResponseEntity addBet(@RequestHeader("playerId") String playerId, @RequestBody
            BetRequest betRequest) {
        if (!isValidBetRequest(betRequest)) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        Optional<Player> player = playerService.getPlayerById(playerId);
        Optional<Roulette> roulette = rouletteService.getRouletteById(betRequest.getRouletteId());
        int number = Integer.parseInt(betRequest.getNumber());
        float amount = Float.parseFloat(betRequest.getAmount());

        if (!player.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Player not found");
        }
        if (!roulette.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Roulette not found");
        }

        if (player.get().getCredit() < amount) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("player has not enough credit");
        }

        if (!roulette.get().getIsOpen()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Roulette is closed");
        }

        if (number < 1 || number > 36) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Number is not in range ");
        }

        if (EnumUtils.stringToColorEnum(betRequest.getColor()) == Bet.ColorEnum.INVALID) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Color is not valid");
        }

        Boolean success;
        success = betService.newBet(roulette.get(), player.get(), amount, number, Bet.ColorEnum.valueOf(betRequest.getColor()));

        if (!success) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("an error occurred creating the bet ");
        }

        playerService.subtractCredit(player.get(), amount);

        return new ResponseEntity<>(null, null, HttpStatus.OK);

    }

    @PatchMapping("")
    public ResponseEntity closeBets(@RequestParam String rouletteId) {
        Boolean isRouletteClosed = rouletteService.close(rouletteId);
        if (!isRouletteClosed) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Roulette not found ");
        }
        List<Bet> bets = betService.closeBets(rouletteId);
        return new ResponseEntity<>(bets, HttpStatus.OK);
    }


}
