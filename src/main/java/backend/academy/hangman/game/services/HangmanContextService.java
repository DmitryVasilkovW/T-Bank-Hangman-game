package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.HangmanGameContext;

public interface HangmanContextService {
    HangmanGameContext decreaseAttempts(HangmanGameContext hangmanGameContext);
    HangmanGameContext addNewPartOfHangman(HangmanGameContext hangmanGameContext);
    boolean hasAttempt(HangmanGameContext hangmanGameContext);
}
