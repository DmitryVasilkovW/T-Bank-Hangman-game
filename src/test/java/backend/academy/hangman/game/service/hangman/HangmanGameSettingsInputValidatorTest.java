package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.exception.AttemptsAreNotSetException;
import backend.academy.hangman.game.exception.CategoryIsNotSetException;
import backend.academy.hangman.game.exception.DifficultyIsNotSetException;
import backend.academy.hangman.game.exception.IncorrectAttemptsException;
import backend.academy.hangman.game.exception.IncorrectCategoryException;
import backend.academy.hangman.game.exception.IncorrectDifficultyException;
import backend.academy.hangman.game.service.impl.hangman.HangmanGameSettingsInputValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HangmanGameSettingsInputValidatorTest {
    private HangmanGameSettingsInputValidatorImpl validator;
    private Set<String> validCategories;
    private Set<String> difficulties;

    @BeforeEach
    void setUp() {
        validator = new HangmanGameSettingsInputValidatorImpl();
        validCategories = Set.of("animals", "fruits");
        difficulties = Set.of("easy", "medium", "hard");
    }

    @Test
    void validateCategoryWhenCategoryIsEmptyThenThrowCategoryIsNotSetException() {
        assertThatThrownBy(() -> validator.validateCategory("", validCategories))
            .isInstanceOf(CategoryIsNotSetException.class);
    }

    @Test
    void validateCategoryWhenCategoryIsInvalidThenThrowIncorrectCategoryException() {
        assertThatThrownBy(() -> validator.validateCategory("InvalidCategory", validCategories))
            .isInstanceOf(IncorrectCategoryException.class);
    }

    @Test
    void validateCategoryWhenCategoryIsValidThenNoException() {
        assertDoesNotThrow(() -> validator.validateCategory("animals", validCategories));
    }

    @Test
    void validateDifficultyWhenDifficultyIsEmptyThenThrowDifficultyIsNotSetException() {
        assertThatThrownBy(() -> validator.validateDifficulty("", difficulties))
            .isInstanceOf(DifficultyIsNotSetException.class);
    }

    @Test
    void validateDifficultyWhenDifficultyIsInvalidThenThrowIncorrectDifficultyException() {
        assertThatThrownBy(() -> validator.validateDifficulty("Extreme", difficulties))
            .isInstanceOf(IncorrectDifficultyException.class);
    }

    @Test
    void validateDifficultyWhenDifficultyIsValidThenNoException() {
        assertDoesNotThrow(() -> validator.validateDifficulty("medium", difficulties));
    }

    @Test
    void validateAttemptsWhenAttemptsAreEmptyThenThrowAttemptsAreNotSetException() {
        assertThatThrownBy(() -> validator.validateAttempts(""))
            .isInstanceOf(AttemptsAreNotSetException.class);
    }

    @Test
    void validateAttemptsWhenAttemptsAreNotIntegerThenThrowIncorrectAttemptsException() {
        assertThatThrownBy(() -> validator.validateAttempts("dva239tri"))
            .isInstanceOf(IncorrectAttemptsException.class)
            .hasMessageContaining("Attempts must be an integer");
    }

    @Test
    void validateAttemptsWhenAttemptsAreOutOfRangeThenThrowIncorrectAttemptsException() {
        assertThatThrownBy(() -> validator.validateAttempts("239"))
            .isInstanceOf(IncorrectAttemptsException.class)
            .hasMessageContaining("Attempts must be between 1 and 26");
    }

    @Test
    void validateAttemptsWhenAttemptsAreValidThenNoException() {
        assertDoesNotThrow(() -> validator.validateAttempts("6"));
    }
}
