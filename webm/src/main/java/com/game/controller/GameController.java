package com.game.controller;

import com.game.service.GameService;
import com.game.util.AttributeNames;
import com.game.util.GameMappings;
import com.game.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {

    public final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(GameMappings.PLAY_NUMBER_GAME)
    public String play_number_game(Model model)
    {
        model.addAttribute(AttributeNames.MAIN_MESSAGE_N, gameService.getMainMessageN());
        model.addAttribute(AttributeNames.RESULT_MESSAGE_N, gameService.getResultMessageN());
        log.info("model = {}",model);

        if(gameService.isNumberGameOver()) {
            return ViewNames.NUMBER_GAME_OVER;
        }
        return ViewNames.PLAY_NUMBER_GAME;
    }

    @PostMapping(GameMappings.PLAY_NUMBER_GAME)
    public String processMessage(@RequestParam int guess)
    {
        log.info("guess {}",guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY_N;
    }

    @GetMapping(GameMappings.RESTART_NUMBER_GAME)
    public String restart_number_game() {
        gameService.reset();
        return GameMappings.REDIRECT_PLAY_N;
    }

    @GetMapping(GameMappings.PLAY_LETTER_GAME)
    public String play_letter_game(Model model)
    {
        model.addAttribute(AttributeNames.MAIN_MESSAGE_L, gameService.getMainMessageL());
        model.addAttribute(AttributeNames.RESULT_MESSAGE_L, gameService.getResultMessageL());
        log.info("model = {}",model);

        if(gameService.isLetterGameOver()) {
            return ViewNames.LETTER_GAME_OVER;
        }
        return ViewNames.PLAY_LETTER_GAME;
    }

    @PostMapping(GameMappings.PLAY_LETTER_GAME)
    public String processMessage(@RequestParam char letterGuess)
    {
        log.info("guess {}",letterGuess);
        gameService.checkGuessLG(letterGuess);
        return GameMappings.REDIRECT_PLAY_L;
    }

    @GetMapping(GameMappings.RESTART_LETTER_GAME)
    public String restart_letter_game() {
        gameService.resetLG();
        return GameMappings.REDIRECT_PLAY_L;
    }
}
