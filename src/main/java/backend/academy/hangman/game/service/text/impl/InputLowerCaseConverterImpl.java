package backend.academy.hangman.game.service.text.impl;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.text.InputConverter;

public class InputLowerCaseConverterImpl implements InputConverter {

    @Override
    public Input convert(Input input) {
        char letter = input.input();

        return new Input(Character.toLowerCase(letter));
    }

    @Override
    public String convert(String input) {
        return input.toLowerCase();
    }
}
