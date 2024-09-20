package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;

public interface HangmanGameInputValidator {

    boolean hasInputAccepted(Input text, Word word);

    boolean isValidLengthOfInput(String input);

    boolean isInputValueOfHintButton(String input);
}
