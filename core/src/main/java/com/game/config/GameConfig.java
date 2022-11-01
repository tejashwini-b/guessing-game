package com.game.config;

import com.game.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/numberGame.properties")
@PropertySource("classpath:config/letterGame.properties")
@ComponentScan(basePackages = "com.game")
public class GameConfig {
    @Value("${numberGame.maxNumber:100}")
    private int maxNumber;
    @Value("${numberGame.guessCount:10}")
    private int guessCount;
    @Value("${numberGame.minNumber:0}")
    private int minNumber;

    @Value("${letterGame.maxLetter:122}")
    private int maxLetter;
    @Value("${letterGame.minLetter:97}")
    private int minLetter;
    @Value("${letterGame.guessCountLG:5}")
    private int guessCountLG;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }

    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }

    @Bean
    @MaxLetter
    public int maxLetter() { return maxLetter; }

    @Bean
    @MinLetter
    public int minLetter() { return minLetter; }

    @Bean
    @GuessCountLG
    public int guessCountLG() { return guessCountLG; }
}
