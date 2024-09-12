package backend.academy.hangman.game.services;

public interface HangmanGameFactory {
    HangmanGameService createHangmanGame(int attempts, String expectedWord);
}
