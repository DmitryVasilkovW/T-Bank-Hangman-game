package backend.academy.hangman.game.services;

import backend.academy.hangman.game.myExceptions.AttemptsAreNotSetException;
import backend.academy.hangman.game.myExceptions.CategoryIsNotSetException;
import backend.academy.hangman.game.myExceptions.DifficultyIsNotSetException;
import backend.academy.hangman.game.myExceptions.IncorrectAttemptsException;
import backend.academy.hangman.game.myExceptions.IncorrectCategoryException;
import backend.academy.hangman.game.myExceptions.IncorrectDifficultyException;
import java.util.Set;

public interface HangmanGameSettingsInputValidator {
    void validateCategory(String category, Set<String> categories) throws IncorrectCategoryException, CategoryIsNotSetException;
    void validateDifficulty(String difficulty, Set<String> difficulties) throws IncorrectDifficultyException, DifficultyIsNotSetException;
    void validateAttempts(String attempts) throws IncorrectAttemptsException, AttemptsAreNotSetException;
}
