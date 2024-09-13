package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.services.HangmanGameFactory;
import backend.academy.hangman.game.services.HangmanGameService;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.CharacterInputValidatorImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIReaderImlp;
import backend.academy.hangman.game.services.impl.text.GuessedLettersServiceImpl;
import backend.academy.hangman.game.services.impl.text.InputLowerCaseConverterImpl;
import backend.academy.hangman.game.services.impl.text.StringAttemptsRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringGuessedWordRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringHangmanRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringInputConverterImpl;
import backend.academy.hangman.game.services.impl.text.StringInputLengthValidatorImpl;

public class CLIHangmanGameFactoryImpl implements HangmanGameFactory {

    @Override
    public HangmanGameService createHangmanGame(int attempts, String expectedWord) {
        var hangman = new char[28];

        for (int i = 0; i < 28; i++) {
            hangman[i] = ' ';
        }

        var hangmanContext = new HangmanContextFactoryImpl().createHangmanGameContext(attempts, expectedWord);
        var hangmanStateService = new HangmanStateServiceImpl(26 - attempts);
        var guessedLettersService = new GuessedLettersServiceImpl();
        var contextService = new HangmanContextServiceImpl(hangmanStateService, guessedLettersService);
        var stringHangmanRender = new StringHangmanRenderImpl();
        var reader = new ScannerCLIReaderImlp();
        var printer = new CLISpringPrinterImpl();
        var characterInputValidator = new CharacterInputValidatorImpl();
        var stringContextRenderer = new StringAttemptsRenderImpl();
        var stringGuessedLettersRender = new StringGuessedWordRenderImpl();
        var lowerCaseConverter = new InputLowerCaseConverterImpl();
        var stringConverter = new StringInputConverterImpl();
        var lengthValidator = new StringInputLengthValidatorImpl();

        return new HangmanGameServiceImpl(
            hangmanContext,
            reader,
            characterInputValidator,
            contextService,
            printer,
            stringHangmanRender,
            stringContextRenderer,
            stringGuessedLettersRender,
            lowerCaseConverter,
            stringConverter,
            lengthValidator
        );
    }
}
