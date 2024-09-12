package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.Hangman;
import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.GuessedLettersService;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.HangmanStateService;

public class HangmanContextServiceImpl implements HangmanContextService {
    private final HangmanStateService hangmanStateService;
    private final GuessedLettersService guessedLettersService;

    public HangmanContextServiceImpl(
        HangmanStateService hangmanStateService,
        GuessedLettersService guessedLettersService
    ) {
        this.hangmanStateService = hangmanStateService;
        this.guessedLettersService = guessedLettersService;
    }

    @Override
    public HangmanGameContext decreaseAttempts(HangmanGameContext hangmanGameContext) {
        return updateContext(hangmanGameContext, hangmanGameContext.attempts() - 1);
    }

    @Override
    public HangmanGameContext addNewPartOfHangman(HangmanGameContext hangmanGameContext) {
        return hangmanStateService.addPart(hangmanGameContext);
    }

    @Override
    public HangmanGameContext updateGuessedLetters(HangmanGameContext hangmanGameContext, Input input) {
        return updateContext(hangmanGameContext, guessedLettersService.updateWord(hangmanGameContext.guessedLetters(), input));
    }

    @Override
    public boolean hasAttempt(HangmanGameContext hangmanGameContext) {
        return hangmanGameContext.attempts() > 0;
    }

    private HangmanGameContext updateContext(HangmanGameContext context, int attempts) {
        return new HangmanGameContext(attempts, context.expectedWord(), context.hangman(), context.guessedLetters());
    }

    private HangmanGameContext updateContext(HangmanGameContext context, Word guessLetters) {
        return new HangmanGameContext(context.attempts(), context.expectedWord(), context.hangman(), guessLetters);
    }
}
