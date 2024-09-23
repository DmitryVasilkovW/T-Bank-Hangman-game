package backend.academy.hangman.game.service.impl.text;

import backend.academy.hangman.game.service.StringInputValidator;

public class StringInputLengthValidatorImpl implements StringInputValidator {

    @Override
    public boolean isValid(String input) {
        return input.length() == 1;
    }
}
