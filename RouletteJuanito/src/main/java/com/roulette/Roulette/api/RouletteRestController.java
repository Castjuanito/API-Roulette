package com.roulette.Roulette.api;

import com.roulette.Roulette.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roulette")
public class RouletteRestController {
    @Autowired
    private RouletteService rouletteService;

    @GetMapping("")
    public ResponseEntity getAllRoulettes() {
        return new ResponseEntity(rouletteService.getAllRoulettes(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity newRoulette() {
        String newRouletteId = rouletteService.newRoulette();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("New roulette with ID: " + newRouletteId);
    }

    @PatchMapping("/open")
    public ResponseEntity openRoulette(@RequestParam String rouletteId) {
        Boolean isRouletteOpen = rouletteService.open(rouletteId);
        if (!isRouletteOpen) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Roulette not found ");
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @PatchMapping("/close")
    public ResponseEntity closeRoulette(@RequestParam String rouletteId) {
        Boolean isRouletteClosed = rouletteService.close(rouletteId);
        if (!isRouletteClosed) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Roulette not found ");
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

}
