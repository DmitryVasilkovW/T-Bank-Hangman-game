package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.services.StringInputLengthValidator;

public class StringInputLengthValidatorImpl implements StringInputLengthValidator {

    @Override
    public boolean isValid(String input) {
        return !input.isEmpty() && input.length() <= 1;
    }
}
