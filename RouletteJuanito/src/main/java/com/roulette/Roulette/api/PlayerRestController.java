package com.roulette.Roulette.api;

import com.roulette.Roulette.model.Player;
import com.roulette.Roulette.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerRestController {

    @Autowired
    PlayerService playerService;

    @GetMapping("")
    public ResponseEntity getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity(players, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity newPlayer(@RequestParam float credit) {
        String newplayerId = playerService.newPlayer(credit);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("New player with ID: " + newplayerId);
    }

}
