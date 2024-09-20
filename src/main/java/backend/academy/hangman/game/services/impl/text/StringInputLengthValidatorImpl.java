package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.services.StringInputValidator;

public class StringInputLengthValidatorImpl implements StringInputValidator {

    @Override
    public boolean isValid(String input) {
        return input.length() == 1;
    }
}
