package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.HangmanGameContext;

public interface StringHangmanGameContextRender {

    String renderHangman(HangmanGameContext context);

    String renderGuessedLetters(HangmanGameContext context);

    String renderAttempts(HangmanGameContext context);

    String renderHint(HangmanGameContext context);
}
