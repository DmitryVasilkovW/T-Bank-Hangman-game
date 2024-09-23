package backend.academy.hangman.game.service.impl.text;

import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Renderable;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.StringRender;

public class StringGuessedWordRenderImpl implements StringRender {

    @Override
    public String render(Renderable renderable) {
        var sb = new StringBuilder();

        Word expectedWord = ((HangmanGameContext) renderable).expectedWord();
        Word guessedLetters = ((HangmanGameContext) renderable).guessedLetters();
        var iterator = new WordIterator(expectedWord);

        while (iterator.hasNext()) {
            char letter = iterator.next();

            if (isContainLetter(letter, guessedLetters)) {
                sb
                    .append(letter)
                    .append(' ');
            } else {
                sb
                    .append('_')
                    .append(' ');
            }
        }


        return sb.toString();
    }

    private boolean isContainLetter(char letter, Word guessedLetters) {
        var iterator = new WordIterator(guessedLetters);

        while (iterator.hasNext()) {
            if (iterator.next() == letter) {
                return true;
            }
        }

        return false;
    }
}
