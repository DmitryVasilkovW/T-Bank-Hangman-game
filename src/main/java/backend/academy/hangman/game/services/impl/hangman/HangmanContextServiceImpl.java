package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.GuessedLettersService;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.HangmanStateService;
import backend.academy.hangman.game.services.impl.text.WordIterator;

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
        return updateContext(
                hangmanGameContext,
                guessedLettersService.updateWord(hangmanGameContext.guessedLetters(),
                        input)
        );
    }

    private HangmanGameContext updateContext(HangmanGameContext context, int attempts) {
        return new HangmanGameContext(
                attempts,
                context.expectedWord(),
                context.hangman(),
                context.guessedLetters(),
                context.hint()
        );
    }

    private HangmanGameContext updateContext(HangmanGameContext context, Word guessLetters) {
        return new HangmanGameContext(context.attempts(), context.expectedWord(), context.hangman(), guessLetters,
                context.hint());
    }

    @Override
    public boolean hasAttempt(HangmanGameContext hangmanGameContext) {
        return hangmanGameContext.attempts() > 0;
    }

    @Override
    public boolean isWordGuessed(HangmanGameContext hangmanGameContext) {
        var expectedWordIterator = new WordIterator(hangmanGameContext.expectedWord());

        while (expectedWordIterator.hasNext()) {
            char letter = expectedWordIterator.next();

            if (!isContainLetter(letter, hangmanGameContext.guessedLetters())) {
                return false;
            }
        }

        return true;
    }

    private boolean isContainLetter(char letter, Word guessedLetters) {
        var iterator = new WordIterator(guessedLetters);

        while (iterator.hasNext()) {
            if (iterator.next() == letter) {
                return true;
            }
        }

        return false;
    }
}
