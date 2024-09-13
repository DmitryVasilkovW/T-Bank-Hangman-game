package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.services.StringInputConverter;

public class StringInputConverterImpl implements StringInputConverter {

    @Override
    public Input convert(String input) {
        return new Input(input.charAt(0));
    }
}
