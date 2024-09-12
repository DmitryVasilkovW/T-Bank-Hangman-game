package backend.academy;

import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.GuessedLettersService;
import backend.academy.hangman.game.services.impl.hangman.HangmanContextFactory;
import backend.academy.hangman.game.services.impl.hangman.HangmanContextServiceImpl;
import backend.academy.hangman.game.services.impl.hangman.HangmanGameServiceImpl;
import backend.academy.hangman.game.services.impl.hangman.HangmanStateServiceImpl;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.InputValidatorImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIReaderImlp;
import backend.academy.hangman.game.services.impl.text.GuessedLettersServiceImlp;
import backend.academy.hangman.game.services.impl.text.StringAttemptsRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringGuessedWordRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringHangmanRenderImpl;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        var hangman = new char[28];
        String expectedWord = "qwe";
        int attempts = 2;
        var word = new char[expectedWord.toString().length()];

        for (int i = 0; i < 28; i++) {
            hangman[i] = ' ';
        }

        for (int i = 0; i < word.length; i++) {
            word[i] = '_';
        }

        var hangmanContext = new HangmanContextFactory().createHangmanGameContext(attempts, expectedWord);
        var hangmanStateService = new HangmanStateServiceImpl(26 - attempts);
        var guessedLettersService = new GuessedLettersServiceImlp();
        var contextService = new HangmanContextServiceImpl(hangmanStateService, guessedLettersService);
        var render = new StringHangmanRenderImpl();
        var reader = new ScannerCLIReaderImlp();
        var printer = new CLISpringPrinterImpl();
        var validator = new InputValidatorImpl();
        var stringContextRenderer = new StringAttemptsRenderImpl();
        var stringGuessedLettersRender = new StringGuessedWordRenderImpl();

        var service = new HangmanGameServiceImpl(hangmanContext, reader, validator, contextService, printer, render, stringContextRenderer, stringGuessedLettersRender);
        service.move();
    }
}


