package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.services.HangmanContextService;

public class HangmanContextServiceImpl implements HangmanContextService {

    @Override
    public HangmanGameContext decreaseAttempts(HangmanGameContext hangmanGameContext) {
        return new HangmanGameContext(hangmanGameContext.attempts() - 1, hangmanGameContext.expectedWord(), hangmanGameContext.hangman());
    }

    @Override
    public HangmanGameContext addNewPartOfHangman(HangmanGameContext hangmanGameContext) {
        return null;
    }

    @Override
    public boolean hasAttempt(HangmanGameContext hangmanGameContext) {
        return hangmanGameContext.attempts() > 0;
    }
}
