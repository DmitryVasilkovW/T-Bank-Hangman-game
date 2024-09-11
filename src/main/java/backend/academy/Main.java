package backend.academy;

import backend.academy.hangman.game.models.Hangman;
import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.impl.hangman.HangmanContextServiceImpl;
import backend.academy.hangman.game.services.impl.hangman.HangmanGameServiceImpl;
import backend.academy.hangman.game.services.impl.hangman.HangmanStateServiceImpl;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.InputValidatorImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIReaderImlp;
import backend.academy.hangman.game.services.impl.text.ExpectedWordIterator;
import backend.academy.hangman.game.services.impl.text.StringHangmanRenderImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        var hangman = new char[28];
        var expectedWord = new Word("qwe");

        for (int i = 0; i < 28; i++) {
            hangman[i] = ' ';
        }

        var hangmanContext = new HangmanGameContext(6, expectedWord, new Hangman(hangman));
        var hangmanStateService = new HangmanStateServiceImpl();
        var contextService = new HangmanContextServiceImpl(hangmanStateService);
        var render = new StringHangmanRenderImpl();
        var reader = new ScannerCLIReaderImlp();
        var printer = new CLISpringPrinterImpl();
        var validator = new InputValidatorImpl();

        var service = new HangmanGameServiceImpl(hangmanContext, reader, validator, contextService, render, printer);
        service.move();
    }
}


