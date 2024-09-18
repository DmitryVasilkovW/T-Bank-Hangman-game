package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.services.StringInputValidator;

public class HintInputValidatorImpl implements StringInputValidator {

    @Override
    public boolean isValid(String input) {
        return input.equals("1");
    }
}
