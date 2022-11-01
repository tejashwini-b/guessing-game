package com.game.console;

import com.game.LetterGame;
import com.game.MessageGenerator;
import com.game.NumberGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleGuessing {

    private final NumberGame numberGame;

    private final LetterGame letterGame;
    private final MessageGenerator messageGenerator;
    // @Autowired


    public ConsoleGuessing(NumberGame numberGame, LetterGame letterGame, MessageGenerator messageGenerator) {
        this.numberGame = numberGame;
        this.letterGame = letterGame;
        this.messageGenerator = messageGenerator;
    }

    // == events ==
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() --> Container ready for use.");

        Scanner scanner = new Scanner(System.in);

            System.out.println("Which game do you want to play?");
            String whichGame = scanner.nextLine().trim();
        while(true) {
            if (whichGame.equalsIgnoreCase("n")) {
                while (true) {
                    System.out.println(messageGenerator.getMainMessageNumber());
                    System.out.println(messageGenerator.getResultMessageNumber());

                    int guess = scanner.nextInt();
                    scanner.nextLine();
                    numberGame.setGuess(guess);
                    numberGame.check();

                    if (numberGame.isGameWon() || numberGame.isGameLost()) {
                        System.out.println(messageGenerator.getResultMessageNumber());
                        System.out.println("Play again y/n?");

                        String playAgainString = scanner.nextLine().trim();
                        if (!playAgainString.equalsIgnoreCase("y")) {
                            return;
                        }
                        System.out.println("Which game do you want to play?");
                        whichGame = scanner.nextLine().trim();
                        if (whichGame.equalsIgnoreCase("n")) {
                            numberGame.reset();
                            break;
                        } else {
                            letterGame.resetLG();
                            break;
                        }
                    }
                }
            } else {
                while (true) {
                    System.out.println(messageGenerator.getMainMessageLetter());
                    System.out.println(messageGenerator.getResultMessageLetter());

                    char letterGuess = scanner.next().charAt(0);
                    letterGuess = Character.toLowerCase(letterGuess);
                    scanner.nextLine();
                    letterGame.setLetterGuess(letterGuess);
                    letterGame.checkLG();

                    if (letterGame.isLGameWon() || letterGame.isLGameLost()) {
                        System.out.println(messageGenerator.getResultMessageLetter());
                        System.out.println("Play again y/n?");
                        String playAgain = scanner.nextLine().trim();
                        if (!playAgain.equalsIgnoreCase("y")) {
                            return;
                        }
                        System.out.println("Which game do you want to play?");
                        whichGame = scanner.nextLine().trim();
                        if (whichGame.equalsIgnoreCase("n")) {
                            numberGame.reset();
                            break;
                        } else {
                            letterGame.resetLG();
                            break;
                        }
                    }
                }
            }
        }
    }
}

