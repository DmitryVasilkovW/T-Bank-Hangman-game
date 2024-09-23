package backend.academy.hangman.game.service.impl.text;

import backend.academy.hangman.game.service.StringConverter;

public class StringLowerCaseConverterImpl implements StringConverter {

    @Override
    public String convert(String word) {
        return word.toLowerCase();
    }
}
