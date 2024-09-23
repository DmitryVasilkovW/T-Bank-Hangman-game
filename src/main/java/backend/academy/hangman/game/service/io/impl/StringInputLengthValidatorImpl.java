package backend.academy.hangman.game.service.io.impl;

import backend.academy.hangman.game.service.io.StringInputValidator;

public class StringInputLengthValidatorImpl implements StringInputValidator {

    @Override
    public boolean isValid(String input) {
        return input.length() == 1;
    }
}
