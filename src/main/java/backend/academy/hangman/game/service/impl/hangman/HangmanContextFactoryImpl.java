package backend.academy.hangman.game.service.impl.hangman;

import backend.academy.hangman.game.model.Hangman;
import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.HangmanGameContextFactory;
import java.util.Arrays;

public class HangmanContextFactoryImpl implements HangmanGameContextFactory {
    private final static int TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN_WITH_INVISIBLE_PARTS = 28;
    private final static int TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN = 26;

    @Override
    public HangmanGameContext createHangmanGameContext(int attempts, String expectedWord, String hint) {
        var service = new HangmanStateServiceImpl();
        var word = new Word(expectedWord.toCharArray());
        var clue = new Word(hint.toCharArray());
        var guessedLetters = new Word(new char[expectedWord.length()]);
        var hangman = new char[TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN_WITH_INVISIBLE_PARTS];
        var hangmanRec = new Hangman(hangman);
        var context = new HangmanGameContext(attempts, word, hangmanRec, guessedLetters, clue);

        Arrays.fill(hangman, ' ');

        for (int i = 0; i < TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN - attempts; i++) {
            context = service.addPart(context);
        }

        return context;
    }
}
