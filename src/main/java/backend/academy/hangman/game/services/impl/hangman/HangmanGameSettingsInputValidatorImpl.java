package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.myExceptions.AttemptsAreNotSetException;
import backend.academy.hangman.game.myExceptions.CategoryIsNotSetException;
import backend.academy.hangman.game.myExceptions.DifficultyIsNotSetException;
import backend.academy.hangman.game.myExceptions.IncorrectAttemptsException;
import backend.academy.hangman.game.myExceptions.IncorrectCategoryException;
import backend.academy.hangman.game.myExceptions.IncorrectDifficultyException;
import backend.academy.hangman.game.services.HangmanGameSettingsInputValidator;
import java.util.Set;

public class HangmanGameSettingsInputValidatorImpl implements HangmanGameSettingsInputValidator {

    @Override
    public void validateCategory(String category, Set<String> categories)
        throws IncorrectCategoryException, CategoryIsNotSetException {
        if (category.isEmpty()) {
            throw new CategoryIsNotSetException();
        } else if (!categories.contains(category)) {
            throw new IncorrectCategoryException();
        }
    }

    @Override
    public void validateDifficulty(String difficulty, Set<String> difficulties)
        throws IncorrectDifficultyException, DifficultyIsNotSetException {
        if (difficulty.isEmpty()) {
            throw new DifficultyIsNotSetException();
        } else if (!difficulties.contains(difficulty)) {
            throw new IncorrectDifficultyException();
        }
    }

    @Override
    public void validateAttempts(String attempts)
        throws IncorrectAttemptsException, AttemptsAreNotSetException {
        if (attempts.isEmpty()) {
            throw new AttemptsAreNotSetException();
        } else {
            try {
                Integer.parseInt(attempts);
            } catch (NumberFormatException e) {
                throw new IncorrectAttemptsException("Attempts must be an integer");
            }

            int attemptCount = Integer.parseInt(attempts);

            if (attemptCount < 1 || attemptCount > 26) {
                throw new IncorrectAttemptsException("Attempts must be between 1 and 26");
            }
        }
    }
}
