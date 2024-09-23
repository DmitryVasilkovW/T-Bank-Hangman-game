package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.service.hangman.impl.HangmanGameLauncher;

public interface GameLauncherFactory {
    HangmanGameLauncher createGameLauncher();
}
