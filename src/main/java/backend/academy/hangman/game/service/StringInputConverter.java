package backend.academy.hangman.game.service;

import backend.academy.hangman.game.model.Input;

public interface StringInputConverter {
    Input convert(String input);
}
