package com.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private static NumberGame numberGame;
    private static LetterGame letterGame;

    @Autowired
    public MessageGeneratorImpl(NumberGame numberGame, LetterGame letterGame) {

        this.numberGame = numberGame;
        this.letterGame = letterGame;
    }

    // == init ==
    @PostConstruct
    public void init() {
        log.info("game = {}", numberGame);
    }


    @Override
    public String getMainMessageNumber() {
        return "Number is between " +
                numberGame.getSmallest() +
                " and " +
                numberGame.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessageNumber() {

        if(numberGame.isGameWon()) {
            return "You guessed it! The number was " + numberGame.getNumber();
        } else if(numberGame.isGameLost()) {
            return "You lost. The number was " + numberGame.getNumber();
        } else if(!numberGame.isValidNumberRange()) {
            return "Invalid number range!";
        } else if(numberGame.getRemainingGuesses() == numberGame.getGuessCount()) {
            return "What is your first guess?";
        } else {
            String direction = "Lower";

            if(numberGame.getGuess() < numberGame.getNumber()) {
                direction = "Higher";
            }

            return direction + "! You have " + numberGame.getRemainingGuesses() + " guesses left";
        }
    }

    @Override
    public String getMainMessageLetter() {
        return "Letter is between " +
                letterGame.getSmallestLetter() +
                " and " +
                letterGame.getBiggestLetter() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessageLetter() {

        if(letterGame.isLGameWon()) {
            return "You guessed it! The letter was " + letterGame.getLetter();
        } else if(letterGame.isLGameLost()) {
            return "You lost. The letter was " + letterGame.getLetter();
        } else if(!letterGame.isValidLetterRange()) {
            return "Invalid letter range!";
        } else if(letterGame.getRemainingGuessesLG() == letterGame.getGuessCountLG()) {
            return "What is your first guess?";
        } else {
            String direction = "Lower";

            if(Character.getNumericValue(letterGame.getLetterGuess()) < Character.getNumericValue(letterGame.getLetter())) {
                direction = "Higher";
            }

            return direction + "! You have " + letterGame.getRemainingGuessesLG() + " guesses left";
        }
    }
}
