package backend.academy.hangman.game.service;

import backend.academy.hangman.game.model.Input;

public interface InputConverter {
    Input convert(Input input);
}
