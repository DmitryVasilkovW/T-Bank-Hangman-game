package backend.academy.hangman.game.service;

import backend.academy.hangman.game.model.HangmanGameContext;

public interface StringHangmanGameContextRender {

    String renderHangman(HangmanGameContext context);

    String renderGuessedLetters(HangmanGameContext context);

    String renderAttempts(HangmanGameContext context);

    String renderHint(HangmanGameContext context);
}
