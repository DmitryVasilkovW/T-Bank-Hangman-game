package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.Input;

public interface InputConverter {
    Input convert(Input input);
}
