package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;

public interface HangmanContextService {
    HangmanGameContext decreaseAttempts(HangmanGameContext hangmanGameContext);
    HangmanGameContext addNewPartOfHangman(HangmanGameContext hangmanGameContext);
    HangmanGameContext updateGuessedLetters(HangmanGameContext hangmanGameContext, Input input);
    boolean hasAttempt(HangmanGameContext hangmanGameContext);
    boolean isWordGuessed(HangmanGameContext hangmanGameContext);
}
