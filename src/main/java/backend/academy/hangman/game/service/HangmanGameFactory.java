package backend.academy.hangman.game.service;

public interface HangmanGameFactory {
    HangmanGameService createHangmanGame(int attempts, String expectedWord, String hint);
}
