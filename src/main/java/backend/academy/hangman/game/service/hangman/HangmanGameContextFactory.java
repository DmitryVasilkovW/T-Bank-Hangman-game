package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.model.HangmanGameContext;

public interface HangmanGameContextFactory {
    HangmanGameContext createHangmanGameContext(int attempts, String expectedWord, String hint);
}
