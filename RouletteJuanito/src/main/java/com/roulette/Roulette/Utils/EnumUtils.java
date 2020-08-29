package com.roulette.Roulette.Utils;

import com.roulette.Roulette.model.Bet;

public class EnumUtils {
    public static Bet.ColorEnum stringToColorEnum(String color) {
        if (Bet.ColorEnum.RED.name().equalsIgnoreCase(color)) {
            return Bet.ColorEnum.RED;
        }
        if (Bet.ColorEnum.BLACK.name().equalsIgnoreCase(color)) {
            return Bet.ColorEnum.BLACK;
        }
        return Bet.ColorEnum.INVALID;
    }
}
