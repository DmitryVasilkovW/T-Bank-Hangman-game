package backend.academy.hangman.game.service.text;

import backend.academy.hangman.game.model.Input;

public interface InputConverter {
    Input convert(Input input);

    String convert(String input);
}
