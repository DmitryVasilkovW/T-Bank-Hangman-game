package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.Input;

public interface HangmanGameInputConverter {

    Input convertToInput(String input);

    Input convertToLowerCase(Input input);
}
