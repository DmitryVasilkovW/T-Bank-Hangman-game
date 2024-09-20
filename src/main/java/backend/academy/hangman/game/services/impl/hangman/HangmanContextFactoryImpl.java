package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.Hangman;
import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.HangmanGameContextFactory;
import java.util.Arrays;

public class HangmanContextFactoryImpl implements HangmanGameContextFactory {
    private final int TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN_WITH_NVISIBLE_PARTS = 28;
    private final int TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN = 26;

    @Override
    public HangmanGameContext createHangmanGameContext(int attempts, String expectedWord, String hint) {
        var service = new HangmanStateServiceImpl();
        var word = new Word(expectedWord.toCharArray());
        var clue = new Word(hint.toCharArray());
        var guessedLetters = new Word (new char[expectedWord.length()]);
        var hangman = new char[TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN_WITH_NVISIBLE_PARTS];
        var hangmanRec = new Hangman(hangman);
        var context = new HangmanGameContext(attempts, word, hangmanRec, guessedLetters, clue);

        Arrays.fill(hangman, ' ');

        for (int i = 0; i < TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN - attempts; i++) {
            context = service.addPart(context);
        }

        return context;
    }
}
