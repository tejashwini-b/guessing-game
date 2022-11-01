package com.game.service;

import com.game.LetterGame;
import com.game.MessageGenerator;
import com.game.NumberGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final NumberGame numberGame;

    private final LetterGame letterGame;

    private final MessageGenerator messageGenerator;

    @Autowired
    public GameServiceImpl(NumberGame numberGame, LetterGame letterGame, MessageGenerator messageGenerator) {
        this.numberGame = numberGame;
        this.letterGame = letterGame;
        this.messageGenerator = messageGenerator;
    }

    @PostConstruct
    public void init() {
        log.info("number = {}",numberGame.getNumber());
        log.info("letter = {}",letterGame.getLetter());
        log.info("main message= {}",messageGenerator.getMainMessageNumber());
    }
    @Override
    public boolean isNumberGameOver() {
        return numberGame.isGameLost() || numberGame.isGameWon();
    }

    @Override
    public String getMainMessageN() {
        return messageGenerator.getMainMessageNumber();
    }

    @Override
    public String getResultMessageN() {
        return messageGenerator.getResultMessageNumber();
    }

    @Override
    public void checkGuess(int guess) {
        numberGame.setGuess(guess);
        numberGame.check();
    }

    @Override
    public void reset() {
        numberGame.reset();
    }

    @Override
    public boolean isLetterGameOver() {
        return letterGame.isLGameLost() || letterGame.isLGameWon();
    }
    @Override
    public String getMainMessageL() {
        return messageGenerator.getMainMessageLetter();
    }

    @Override
    public String getResultMessageL() {
        return messageGenerator.getResultMessageLetter();
    }

    @Override
    public void checkGuessLG(char letterGuess) {
        letterGame.setLetterGuess(letterGuess);
        letterGame.checkLG();
    }

    @Override
    public void resetLG() {
        letterGame.resetLG();
    }
}
