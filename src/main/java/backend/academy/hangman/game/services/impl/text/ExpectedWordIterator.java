package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.Word;
import java.util.Iterator;

public class ExpectedWordIterator implements Iterator<Character> {
    private Word expectedWord;
    private int index = 0;
    private final char[] wordAsArray = expectedWord.text().toCharArray();

    public ExpectedWordIterator(Word expectedWord) {
        this.expectedWord = expectedWord;
    }

    @Override
    public boolean hasNext() {
        return index < wordAsArray.length;
    }

    @Override
    public Character next() {
        return wordAsArray[++index];
    }
}
