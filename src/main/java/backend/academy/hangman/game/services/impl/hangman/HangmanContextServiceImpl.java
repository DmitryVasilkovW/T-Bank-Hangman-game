package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.HangmanStateService;

public class HangmanContextServiceImpl implements HangmanContextService {
    private final HangmanStateService hangmanStateService;

    public HangmanContextServiceImpl(HangmanStateService hangmanStateService) {
        this.hangmanStateService = hangmanStateService;
    }

    @Override
    public HangmanGameContext decreaseAttempts(HangmanGameContext hangmanGameContext) {
        return new HangmanGameContext(hangmanGameContext.attempts() - 1, hangmanGameContext.expectedWord(), hangmanGameContext.hangman(), hangmanGameContext.word());
    }

    @Override
    public HangmanGameContext addNewPartOfHangman(HangmanGameContext hangmanGameContext) {
        return hangmanStateService.addPart(hangmanGameContext);
    }

    @Override
    public boolean hasAttempt(HangmanGameContext hangmanGameContext) {
        return hangmanGameContext.attempts() > 0;
    }
}
