package backend.academy.hangman.game.service.io.impl;

import backend.academy.hangman.game.service.io.StringInputValidator;

public class HintInputValidatorImpl implements StringInputValidator {
    private final static String VALUE_OF_HINT_BUTTON = "1";

    @Override
    public boolean isValid(String input) {
        return input.equals(VALUE_OF_HINT_BUTTON);
    }
}
