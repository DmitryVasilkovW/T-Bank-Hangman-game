package backend.academy.hangman.game.service.impl.io;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.CharacterInputValidator;
import backend.academy.hangman.game.service.impl.text.WordIterator;

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
