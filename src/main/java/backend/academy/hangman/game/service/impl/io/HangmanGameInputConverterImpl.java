package backend.academy.hangman.game.service.impl.io;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.HangmanGameInputConverter;
import backend.academy.hangman.game.service.InputConverter;
import backend.academy.hangman.game.service.StringInputConverter;

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
