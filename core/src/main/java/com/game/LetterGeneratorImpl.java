package com.game;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LetterGeneratorImpl implements LetterGenerator{

    private final Random random = new Random();

    @Getter
    private final int maxLetter;

    @Getter
    private final int minLetter;

    @Autowired
    public LetterGeneratorImpl(@MaxLetter int maxLetter, @MinLetter int minLetter) {
        this.maxLetter = maxLetter;
        this.minLetter = minLetter;
    }

    @Override
    public char nextLetter() {
       /* return  (char)(random.nextInt(Character.getNumericValue(maxLetter) -
                        Character.getNumericValue(minLetter)) - Character.getNumericValue(minLetter));*/
        return (char) (random.nextInt(maxLetter - minLetter) + minLetter);
    }
}
