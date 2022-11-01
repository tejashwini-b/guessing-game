package com.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class LetterGameImpl implements LetterGame {

    @Getter(AccessLevel.NONE)
    private final LetterGenerator letterGenerator;

    private final int guessCountLG;

    @Autowired
    public LetterGameImpl(LetterGenerator letterGenerator, @GuessCountLG int guessCountLG) {
        this.letterGenerator = letterGenerator;
        this.guessCountLG = guessCountLG;
    }

    private char letter;
    private char smallestLetter;
    private char biggestLetter;
    private int remainingGuessesLG;
    private boolean validLetterRange = true;

    @Setter
    private char letterGuess;

    @PostConstruct
    @Override
    public void resetLG() {
        smallestLetter = (char) letterGenerator.getMinLetter();
        letterGuess = (char) letterGenerator.getMinLetter();
        remainingGuessesLG = guessCountLG;
        biggestLetter = (char) letterGenerator.getMaxLetter();
        letter = letterGenerator.nextLetter();
        log.debug("the letter is {}", letter);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
    }

    @Override
    public void checkLG() {

        checkValidLetterRange();

        if(validLetterRange) {
            if((int)letterGuess > (int) (letter)) {
                biggestLetter = (char) ((int)(letterGuess)-1);
            }

            if((int)(letterGuess) < (int)(letter)) {
                smallestLetter = (char) ((int)(letterGuess)+1);
            }
        }

        remainingGuessesLG--;
    }

    @Override
    public boolean isLGameWon() {
        return letterGuess == letter;
    }

    @Override
    public boolean isLGameLost() {
        return !isLGameWon() && remainingGuessesLG <= 0;
    }

    // == private methods ==
    private void checkValidLetterRange() {
        validLetterRange = ((int) letterGuess >= (int) smallestLetter) && ((int) letterGuess <= (int) biggestLetter);
    }

}
