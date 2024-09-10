package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.Input;

public interface InputHandler {
    Input acceptInput(InputReader reader);
}
