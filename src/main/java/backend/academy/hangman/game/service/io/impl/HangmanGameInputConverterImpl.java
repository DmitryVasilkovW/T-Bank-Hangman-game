package backend.academy.hangman.game.service.io.impl;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.io.HangmanGameInputConverter;
import backend.academy.hangman.game.service.text.InputConverter;
import backend.academy.hangman.game.service.text.StringInputConverter;

public class HangmanGameInputConverterImpl implements HangmanGameInputConverter {

    private final InputConverter inputConverter;
    private final StringInputConverter stringInputConverter;

    public HangmanGameInputConverterImpl(InputConverter inputConverter, StringInputConverter stringInputConverter) {
        this.inputConverter = inputConverter;
        this.stringInputConverter = stringInputConverter;
    }

    @Override
    public Input convertToInput(String input) {
        return stringInputConverter.convert(input);
    }

    @Override
    public Input convertToLowerCase(Input input) {
        return inputConverter.convert(input);
    }
}
