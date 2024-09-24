package backend.academy.hangman.game.service.hangman.impl;

import backend.academy.hangman.game.service.hangman.GameLauncherFactory;
import backend.academy.hangman.game.service.io.impl.CLIStringPrinterImpl;
import backend.academy.hangman.game.service.io.impl.ScannerCLIReaderImlp;
import backend.academy.hangman.game.service.text.impl.InputLowerCaseConverterImpl;

public class CLIHangmanGameLauncherFactoryImpl implements GameLauncherFactory {

    @Override
    public HangmanGameLauncher createGameLauncher() {
        var validator = new HangmanGameSettingsInputValidatorImpl();
        var printer = new CLIStringPrinterImpl();
        var reader = new ScannerCLIReaderImlp();
        var randomSetter = new RandomSetterImpl();
        var converter = new InputLowerCaseConverterImpl();
        var contextFactory = new HangmanContextFactoryImpl();

        return new HangmanGameLauncher(validator, printer, reader, randomSetter, converter, contextFactory);
    }
}
