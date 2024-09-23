package backend.academy.hangman.game.service.impl.text;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.InputConverter;

public class InputLowerCaseConverterImpl implements InputConverter {

    @Override
    public Input convert(Input input) {
        char letter = input.input();

        return new Input(Character.toLowerCase(letter));
    }
}
