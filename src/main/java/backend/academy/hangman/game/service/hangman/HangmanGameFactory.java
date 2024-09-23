package backend.academy.hangman.game.service.hangman;

public interface HangmanGameFactory {
    HangmanGameService createHangmanGame(int attempts, String expectedWord, String hint);
}
