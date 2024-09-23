package backend.academy.hangman.game.service.impl.text;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.StringInputConverter;

public class StringInputConverterImpl implements StringInputConverter {

    @Override
    public Input convert(String input) {
        return new Input(input.charAt(0));
    }
}
