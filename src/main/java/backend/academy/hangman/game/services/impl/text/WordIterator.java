package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.Word;
import java.util.Iterator;

public class WordIterator implements Iterator<Character> {
    private int index = -1;
    private final char[] wordAsArray;

    public WordIterator(Word expectedWord) {
        this.wordAsArray = expectedWord.text();
    }

    @Override
    public boolean hasNext() {
        return index + 1 < wordAsArray.length;
    }

    @Override
    public Character next() {
        return wordAsArray[++index];
    }
}
