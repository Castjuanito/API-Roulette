package com.roulette.Roulette.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Roulette")
public class Roulette implements Serializable {
    private @Id
    String rouletteId;
    private Boolean IsOpen;
}
