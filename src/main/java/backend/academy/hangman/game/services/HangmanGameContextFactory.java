package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.HangmanGameContext;

public interface HangmanGameContextFactory {
    HangmanGameContext createHangmanGameContext(int attempts, String expectedWord, String text);
}
