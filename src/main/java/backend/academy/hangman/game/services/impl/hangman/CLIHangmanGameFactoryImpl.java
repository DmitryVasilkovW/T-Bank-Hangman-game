package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.services.HangmanGameFactory;
import backend.academy.hangman.game.services.HangmanGameService;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.CharacterInputValidatorImpl;
import backend.academy.hangman.game.services.impl.io.HintInputValidatorImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIReaderImlp;
import backend.academy.hangman.game.services.impl.text.GuessedLettersServiceImpl;
import backend.academy.hangman.game.services.impl.text.InputLowerCaseConverterImpl;
import backend.academy.hangman.game.services.impl.text.StringAttemptsRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringGuessedWordRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringHangmanRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringHintRenderImpl;
import backend.academy.hangman.game.services.impl.text.StringInputConverterImpl;
import backend.academy.hangman.game.services.impl.text.StringInputLengthValidatorImpl;

public class CLIHangmanGameFactoryImpl implements HangmanGameFactory {
    private final int TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN = 26;

    @Override
    public HangmanGameService createHangmanGame(int attempts, String expectedWord, String hint) {
        var hangmanContext = new HangmanContextFactoryImpl().createHangmanGameContext(attempts, expectedWord, hint);
        var hangmanStateService = new HangmanStateServiceImpl(TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN - attempts);
        var guessedLettersService = new GuessedLettersServiceImpl();
        var contextService = new HangmanContextServiceImpl(hangmanStateService, guessedLettersService);
        var stringHangmanRender = new StringHangmanRenderImpl();
        var reader = new ScannerCLIReaderImlp();
        var printer = new CLISpringPrinterImpl();
        var characterInputValidator = new CharacterInputValidatorImpl();
        var stringContextRenderer = new StringAttemptsRenderImpl();
        var stringGuessedLettersRender = new StringGuessedWordRenderImpl();
        var stringHintRender = new StringHintRenderImpl();
        var lowerCaseConverter = new InputLowerCaseConverterImpl();
        var stringConverter = new StringInputConverterImpl();
        var lengthValidator = new StringInputLengthValidatorImpl();
        var hintValidator = new HintInputValidatorImpl();

        return new HangmanGameServiceImpl(
            hangmanContext,
            reader,
            characterInputValidator,
            contextService,
            printer,
            stringHangmanRender,
            stringContextRenderer,
            stringGuessedLettersRender,
            stringHintRender,
            lowerCaseConverter,
            stringConverter,
            lengthValidator,
            hintValidator
        );
    }
}
