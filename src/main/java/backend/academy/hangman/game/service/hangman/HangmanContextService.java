package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Input;

public interface HangmanContextService {

    HangmanGameContext decreaseAttempts(HangmanGameContext hangmanGameContext);

    HangmanGameContext addNewPartOfHangman(HangmanGameContext hangmanGameContext);

    HangmanGameContext updateGuessedLetters(HangmanGameContext hangmanGameContext, Input input);

    boolean hasAttempt(HangmanGameContext hangmanGameContext);

    boolean isWordGuessed(HangmanGameContext hangmanGameContext);
}
