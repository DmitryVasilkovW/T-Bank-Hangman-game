package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.model.Hangman;
import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.text.GuessedLettersService;
import backend.academy.hangman.game.service.hangman.impl.HangmanContextServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HangmanContextServiceTest {
    @Mock
    private HangmanStateService hangmanStateService;

    @Mock
    private GuessedLettersService guessedLettersService;

    @InjectMocks
    private HangmanContextServiceImpl hangmanContextService;

    private HangmanGameContext gameContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Word expectedWord = makeWord("test");
        Word guessedLetters = makeWord("te");
        gameContext = new HangmanGameContext(5, expectedWord, new Hangman(new char[28]), guessedLetters, makeWord(""));
    }

    @Test
    void shouldDecreaseAttempts() {
        HangmanGameContext updatedContext = hangmanContextService.decreaseAttempts(gameContext);

        assertThat(updatedContext.attempts()).isEqualTo(4);
    }

    @Test
    void shouldAddNewPartOfHangman() {
        when(hangmanStateService.addPart(gameContext)).thenReturn(gameContext);

        HangmanGameContext updatedContext = hangmanContextService.addNewPartOfHangman(gameContext);

        verify(hangmanStateService).addPart(gameContext);
        assertThat(updatedContext.hangman().hangman()).isNotNull();
    }

    @Test
    void shouldUpdateGuessedLetters() {
        var input = new Input('s');
        Word updatedWord = makeWord("tes");
        when(guessedLettersService.updateWord(gameContext.guessedLetters(), input)).thenReturn(updatedWord);

        HangmanGameContext updatedContext = hangmanContextService.updateGuessedLetters(gameContext, input);

        assertThat(updatedContext.guessedLetters()).isEqualTo(updatedWord);
        verify(guessedLettersService).updateWord(gameContext.guessedLetters(), input);
    }

    @Test
    void shouldReturnTrueWhenWordIsGuessed() {
        var contextWithFullWord = new HangmanGameContext(5, makeWord("test"), new Hangman(new char[28]), makeWord("test"), makeWord(""));

        boolean isWordGuessed = hangmanContextService.isWordGuessed(contextWithFullWord);

        assertThat(isWordGuessed).isTrue();
    }

    @Test
    void shouldReturnFalseWhenWordIsNotGuessed() {
        boolean isWordGuessed = hangmanContextService.isWordGuessed(gameContext);

        assertThat(isWordGuessed).isFalse();
    }

    @Test
    void shouldReturnTrueIfAttemptsRemain() {
        boolean hasAttempt = hangmanContextService.hasAttempt(gameContext);

        assertThat(hasAttempt).isTrue();
    }

    @Test
    void shouldReturnFalseIfNoAttemptsRemain() {
        var contextWithNoAttempts = new HangmanGameContext(0, gameContext.expectedWord(), gameContext.hangman(), gameContext.guessedLetters(), makeWord(""));

        boolean hasAttempt = hangmanContextService.hasAttempt(contextWithNoAttempts);

        assertThat(hasAttempt).isFalse();
    }

    private Word makeWord(String string) {
        return new Word(string.toCharArray());
    }
}
