package backend.academy.hangman.game.service.io;

import backend.academy.hangman.game.model.Input;

public interface HangmanGameInputConverter {

    Input convertToInput(String input);

    Input convertToLowerCase(Input input);
}
