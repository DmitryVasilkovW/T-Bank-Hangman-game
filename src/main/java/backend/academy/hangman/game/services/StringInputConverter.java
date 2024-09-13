package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.Input;

public interface StringInputConverter {
    Input convert(String input);
}
