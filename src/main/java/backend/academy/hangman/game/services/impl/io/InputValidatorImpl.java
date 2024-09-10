package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.InputValidator;
import backend.academy.hangman.game.services.impl.text.ExpectedWordIterator;

public class InputValidatorImpl implements InputValidator {

    @Override
    public boolean hasInputAccepted(Input input, Word expectedWord) {
        var iterator = new ExpectedWordIterator(expectedWord);

        while (iterator.hasNext()) {
            char currentChar = iterator.next();

            if (currentChar == input.input()) {
                return true;
            }
        }

        return false;
    }
}
