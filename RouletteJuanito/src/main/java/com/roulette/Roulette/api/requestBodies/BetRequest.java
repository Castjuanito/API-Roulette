package com.roulette.Roulette.api.requestBodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetRequest {
    private String rouletteId;
    private String color;
    private String number;
    private String amount;
}
