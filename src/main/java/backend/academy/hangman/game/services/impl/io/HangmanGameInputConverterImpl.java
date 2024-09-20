package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.services.HangmanGameInputConverter;
import backend.academy.hangman.game.services.InputConverter;
import backend.academy.hangman.game.services.StringInputConverter;

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
