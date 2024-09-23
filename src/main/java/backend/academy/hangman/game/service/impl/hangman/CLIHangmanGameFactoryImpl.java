package backend.academy.hangman.game.service.impl.hangman;

import backend.academy.hangman.game.service.HangmanGameFactory;
import backend.academy.hangman.game.service.HangmanGameService;
import backend.academy.hangman.game.service.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.service.impl.io.CharacterInputValidatorImpl;
import backend.academy.hangman.game.service.impl.io.HangmanGameInputConverterImpl;
import backend.academy.hangman.game.service.impl.io.HangmanGameInputValidatorImpl;
import backend.academy.hangman.game.service.impl.io.HintInputValidatorImpl;
import backend.academy.hangman.game.service.impl.io.ScannerCLIReaderImlp;
import backend.academy.hangman.game.service.impl.text.GuessedLettersServiceImpl;
import backend.academy.hangman.game.service.impl.text.InputLowerCaseConverterImpl;
import backend.academy.hangman.game.service.impl.text.StringAttemptsRenderImpl;
import backend.academy.hangman.game.service.impl.text.StringGuessedWordRenderImpl;
import backend.academy.hangman.game.service.impl.text.StringHangmanGameContextRenderImpl;
import backend.academy.hangman.game.service.impl.text.StringHangmanRenderImpl;
import backend.academy.hangman.game.service.impl.text.StringHintRenderImpl;
import backend.academy.hangman.game.service.impl.text.StringInputConverterImpl;
import backend.academy.hangman.game.service.impl.text.StringInputLengthValidatorImpl;

public class CLIHangmanGameFactoryImpl implements HangmanGameFactory {

    private final static int TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN = 26;

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
        var stringAttemptsRender = new StringAttemptsRenderImpl();
        var stringGuessedLettersRender = new StringGuessedWordRenderImpl();
        var stringHintRender = new StringHintRenderImpl();
        var lowerCaseConverter = new InputLowerCaseConverterImpl();
        var stringConverter = new StringInputConverterImpl();
        var lengthValidator = new StringInputLengthValidatorImpl();
        var hintValidator = new HintInputValidatorImpl();
        var stringHangmanGameContextRender = new StringHangmanGameContextRenderImpl(
                stringHangmanRender,
                stringGuessedLettersRender,
                stringHintRender,
                stringAttemptsRender
        );
        var validator = new HangmanGameInputValidatorImpl(characterInputValidator, lengthValidator, hintValidator);
        var converter = new HangmanGameInputConverterImpl(lowerCaseConverter, stringConverter);

        return new HangmanGameServiceImpl(
                hangmanContext,
                reader,
                contextService,
                printer,
                stringHangmanGameContextRender,
                converter,
                validator
        );
    }
}
