package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.services.StringConverter;

public class StringLowerCaseConverterImpl implements StringConverter {

    @Override
    public String convert(String word) {
        return word.toLowerCase();
    }
}
