package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.model.Hangman;
import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.text.GuessedLettersService;
import backend.academy.hangman.game.service.hangman.impl.HangmanContextServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HangmanContextServiceTest {
    @Mock
    private HangmanStateService hangmanStateService;

    @Mock
    private GuessedLettersService guessedLettersService;

    private final Word expectedWord = makeWord("test");
    private final Word guessedLetters = makeWord("te");

    @Spy
    private final HangmanGameContext gameContext = new HangmanGameContext(
            5,
            expectedWord,
            new Hangman(new char[28]),
            guessedLetters,
            makeWord("")
    );

    @InjectMocks
    private HangmanContextServiceImpl hangmanContextService;

    @Test
    void shouldDecreaseAttempts() {
        HangmanGameContext updatedContext = hangmanContextService.decreaseAttempts(gameContext);

        assertEquals(updatedContext.attempts(), 4);
    }

    @Test
    void shouldAddNewPartOfHangman() {
        when(hangmanStateService.addPart(gameContext)).thenReturn(gameContext);

        HangmanGameContext updatedContext = hangmanContextService.addNewPartOfHangman(gameContext);

        verify(hangmanStateService).addPart(gameContext);
        assertNotNull(updatedContext.hangman().hangman());
    }

    @Test
    void shouldUpdateGuessedLetters() {
        var input = new Input('s');
        Word updatedWord = makeWord("tes");
        when(guessedLettersService.updateWord(gameContext.guessedLetters(), input)).thenReturn(updatedWord);

        HangmanGameContext updatedContext = hangmanContextService.updateGuessedLetters(gameContext, input);

        assertEquals(updatedContext.guessedLetters(), updatedWord);
        verify(guessedLettersService).updateWord(gameContext.guessedLetters(), input);
    }

    @Test
    void shouldReturnTrueWhenWordIsGuessed() {
        var contextWithFullWord = new HangmanGameContext(
                5,
                makeWord("test"),
                new Hangman(new char[28]),
                makeWord("test"),
                makeWord("")
        );

        boolean isWordGuessed = hangmanContextService.isWordGuessed(contextWithFullWord);

        assertTrue(isWordGuessed);
    }

    @Test
    void shouldReturnFalseWhenWordIsNotGuessed() {
        boolean isWordGuessed = hangmanContextService.isWordGuessed(gameContext);

        assertFalse(isWordGuessed);
    }

    @Test
    void shouldReturnTrueIfAttemptsRemain() {
        boolean hasAttempt = hangmanContextService.hasAttempt(gameContext);

        assertTrue(hasAttempt);
    }

    @Test
    void shouldReturnFalseIfNoAttemptsRemain() {
        var contextWithNoAttempts = new HangmanGameContext(
                0,
                gameContext.expectedWord(),
                gameContext.hangman(),
                gameContext.guessedLetters(),
                makeWord("")
        );

        boolean hasAttempt = hangmanContextService.hasAttempt(contextWithNoAttempts);

        assertFalse(hasAttempt);
    }

    private Word makeWord(String string) {
        return new Word(string.toCharArray());
    }
}
