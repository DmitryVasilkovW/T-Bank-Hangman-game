package backend.academy.hangman.game.service.hangman.impl;

import backend.academy.hangman.game.service.hangman.HangmanGameContextFactory;
import backend.academy.hangman.game.service.hangman.HangmanGameFactory;
import backend.academy.hangman.game.service.hangman.HangmanGameService;
import backend.academy.hangman.game.service.io.impl.CLIStringPrinterImpl;
import backend.academy.hangman.game.service.io.impl.CharacterInputValidatorImpl;
import backend.academy.hangman.game.service.io.impl.HangmanGameInputConverterImpl;
import backend.academy.hangman.game.service.io.impl.HangmanGameInputValidatorImpl;
import backend.academy.hangman.game.service.io.impl.HintInputValidatorImpl;
import backend.academy.hangman.game.service.io.impl.ScannerCLIReaderImlp;
import backend.academy.hangman.game.service.io.impl.StringInputLengthValidatorImpl;
import backend.academy.hangman.game.service.text.impl.GuessedLettersServiceImpl;
import backend.academy.hangman.game.service.text.impl.InputLowerCaseConverterImpl;
import backend.academy.hangman.game.service.text.impl.StringAttemptsRenderImpl;
import backend.academy.hangman.game.service.text.impl.StringGuessedWordRenderImpl;
import backend.academy.hangman.game.service.text.impl.StringHangmanGameContextRenderImpl;
import backend.academy.hangman.game.service.text.impl.StringHangmanRenderImpl;
import backend.academy.hangman.game.service.text.impl.StringHintRenderImpl;
import backend.academy.hangman.game.service.text.impl.StringInputConverterImpl;


public class CLIHangmanGameFactoryImpl implements HangmanGameFactory {

    private final static int TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN = 26;
    private final HangmanGameContextFactory contextFactory;

    public CLIHangmanGameFactoryImpl(HangmanGameContextFactory contextFactory) {
        this.contextFactory = contextFactory;
    }

    @Override
    public HangmanGameService createHangmanGame(int attempts, String expectedWord, String hint) {
        var hangmanContext = contextFactory.createHangmanGameContext(attempts, expectedWord, hint);
        var hangmanStateService = new HangmanStateServiceImpl(TOTAL_NUMBER_OF_PARTS_OF_FULLY_HANGMAN - attempts);
        var guessedLettersService = new GuessedLettersServiceImpl();
        var contextService = new HangmanContextServiceImpl(hangmanStateService, guessedLettersService);
        var stringHangmanRender = new StringHangmanRenderImpl();
        var reader = new ScannerCLIReaderImlp();
        var printer = new CLIStringPrinterImpl();
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
