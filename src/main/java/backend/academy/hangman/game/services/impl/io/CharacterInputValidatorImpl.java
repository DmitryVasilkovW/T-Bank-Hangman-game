package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.CharacterInputValidator;
import backend.academy.hangman.game.services.impl.text.WordIterator;

public class CharacterInputValidatorImpl implements CharacterInputValidator {

    @Override
    public boolean hasInputAccepted(Input input, Word expectedWord) {
        var iterator = new WordIterator(expectedWord);

        while (iterator.hasNext()) {
            char currentChar = iterator.next();

            if (currentChar == input.input()) {
                return true;
            }
        }

        return false;
    }
}
