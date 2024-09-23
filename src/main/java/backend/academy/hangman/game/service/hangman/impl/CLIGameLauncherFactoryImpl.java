package backend.academy.hangman.game.service.hangman.impl;

import backend.academy.hangman.game.service.hangman.GameLauncherFactory;
import backend.academy.hangman.game.service.io.impl.CLIStringPrinterImpl;
import backend.academy.hangman.game.service.io.impl.ScannerCLIReaderImlp;
import backend.academy.hangman.game.service.text.impl.InputLowerCaseConverterImpl;

public class CLIGameLauncherFactoryImpl implements GameLauncherFactory {

    @Override
    public GameLauncher createGameLauncher() {
        var validator = new HangmanGameSettingsInputValidatorImpl();
        var printer = new CLIStringPrinterImpl();
        var reader = new ScannerCLIReaderImlp();
        var randomSetter = new RandomSetterImpl();
        var converter = new InputLowerCaseConverterImpl();

        return new GameLauncher(validator, printer, reader, randomSetter, converter);
    }
}
