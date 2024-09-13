package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.Hangman;
import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.HangmanGameContextFactory;

public class HangmanContextFactoryImpl implements HangmanGameContextFactory {

    @Override
    public HangmanGameContext createHangmanGameContext(int attempts, String expectedWord) {
        var service = new HangmanStateServiceImpl();
        var word = new Word(expectedWord.toCharArray());
        var guessedLetters = new Word (new char[expectedWord.length()]);
        var hangman = new char[28];
        var hangmanRec = new Hangman(hangman);
        var context = new HangmanGameContext(attempts, word, hangmanRec, guessedLetters);

        for (int i = 0; i < hangman.length; i++) {
            hangman[i] = ' ';
        }

        for (int i = 0; i < 26 - attempts; i++) {
            context = service.addPart(context);
        }

        return context;
    }
}
