package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.service.hangman.impl.GameLauncher;

public interface GameLauncherFactory {
    GameLauncher createGameLauncher();
}
