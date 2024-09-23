package backend.academy.hangman.game.service.text.impl;

import backend.academy.hangman.game.service.text.StringConverter;

public class StringLowerCaseConverterImpl implements StringConverter {

    @Override
    public String convert(String word) {
        return word.toLowerCase();
    }
}
