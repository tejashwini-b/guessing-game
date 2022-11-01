package com.game.service;

public interface GameService {
    boolean isNumberGameOver();
    String getMainMessageN();
    String getResultMessageN();
    void checkGuess(int guess);
    void reset();

    boolean isLetterGameOver();
    String getMainMessageL();
    String getResultMessageL();
    void checkGuessLG(char letterGuess);
    void resetLG();
}
