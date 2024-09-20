package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.services.StringInputValidator;

public class HintInputValidatorImpl implements StringInputValidator {
    private final String VALUE_OF_HINT_BUTTON = "1";

    @Override
    public boolean isValid(String input) {
        return input.equals(VALUE_OF_HINT_BUTTON);
    }
}
