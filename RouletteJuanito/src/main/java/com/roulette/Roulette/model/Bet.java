package com.roulette.Roulette.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Bet")
public class Bet implements Serializable {
    @Id
    private String id;
    private int number;
    private ColorEnum color;
    private float amount;
    private Boolean isWinner;
    private @Indexed
    String playerId;
    private @Indexed
    String rouletteId;

    public enum ColorEnum {
        RED,
        BLACK,
        INVALID
    }
}
