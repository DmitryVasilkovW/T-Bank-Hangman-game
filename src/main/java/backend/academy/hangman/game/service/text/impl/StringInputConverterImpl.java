package backend.academy.hangman.game.service.text.impl;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.text.StringInputConverter;

public class StringInputConverterImpl implements StringInputConverter {

    @Override
    public Input convert(String input) {
        return new Input(input.charAt(0));
    }
}
