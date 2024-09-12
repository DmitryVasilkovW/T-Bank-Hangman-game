package backend.academy.hangman.game.services;

public interface GameSetupReader {
    String readSettingForWord();
    int readAttempts();
}
