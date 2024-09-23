package backend.academy.hangman.game.service.io;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;

public interface HangmanGameInputValidator {

    boolean hasInputAccepted(Input text, Word word);

    boolean isValidLengthOfInput(String input);

    boolean isInputValueOfHintButton(String input);
}
