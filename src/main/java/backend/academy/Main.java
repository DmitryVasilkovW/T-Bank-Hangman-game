package backend.academy;

import backend.academy.hangman.game.service.hangman.impl.CLIHangmanGameLauncherFactoryImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        new CLIHangmanGameLauncherFactoryImpl()
                .createGameLauncher()
                .launchGame();
    }
}
