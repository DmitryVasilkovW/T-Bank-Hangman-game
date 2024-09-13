package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.services.InputConverter;

public class InputLowerCaseConverterImpl implements InputConverter {

    @Override
    public Input convert(Input input) {
        char letter = input.input();

        return new Input(Character.toLowerCase(letter));
    }
}
