package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;

public interface InputValidator {
    boolean hasInputAccepted(Input input, Word expectedWord);
}
