package backend.academy.hangman.game.service;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;

public interface CharacterInputValidator {
    boolean hasInputAccepted(Input input, Word expectedWord);
}
