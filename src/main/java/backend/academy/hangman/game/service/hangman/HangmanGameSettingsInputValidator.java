package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.exception.AttemptsAreNotSetException;
import backend.academy.hangman.game.exception.CategoryIsNotSetException;
import backend.academy.hangman.game.exception.DifficultyIsNotSetException;
import backend.academy.hangman.game.exception.IncorrectAttemptsException;
import backend.academy.hangman.game.exception.IncorrectCategoryException;
import backend.academy.hangman.game.exception.IncorrectDifficultyException;
import java.util.Set;

public interface HangmanGameSettingsInputValidator {

    void validateCategory(String category, Set<String> categories)
            throws IncorrectCategoryException, CategoryIsNotSetException;

    void validateDifficulty(String difficulty, Set<String> difficulties)
            throws IncorrectDifficultyException, DifficultyIsNotSetException;

    void validateAttempts(String attempts)
            throws IncorrectAttemptsException, AttemptsAreNotSetException;
}
