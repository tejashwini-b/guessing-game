package com.game;

public interface LetterGame {

    char getLetter();

    char getLetterGuess();

    void setLetterGuess(char letterGuess);

    char getSmallestLetter();

    char getBiggestLetter();

    int getRemainingGuessesLG();

    int getGuessCountLG();

    void resetLG();

    void checkLG();

    boolean isValidLetterRange();

    boolean isLGameWon();

    boolean isLGameLost();
}
