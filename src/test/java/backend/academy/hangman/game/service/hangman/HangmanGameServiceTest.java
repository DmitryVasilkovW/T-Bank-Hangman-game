package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.model.Hangman;
import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.io.HangmanGameInputConverter;
import backend.academy.hangman.game.service.io.HangmanGameInputValidator;
import backend.academy.hangman.game.service.text.StringHangmanGameContextRender;
import backend.academy.hangman.game.service.io.StringPrinter;
import backend.academy.hangman.game.service.io.StringReader;
import backend.academy.hangman.game.service.hangman.impl.HangmanGameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HangmanGameServiceTest {

    @Mock
    private StringReader reader;

    @Mock
    private HangmanGameInputValidator inputValidator;

    @Mock
    private HangmanContextService contextService;

    @Mock
    private StringPrinter stringPrinter;

    @Mock
    private StringHangmanGameContextRender render;

    @Mock
    private HangmanGameInputConverter inputConverter;

    @InjectMocks
    private HangmanGameServiceImpl hangmanGameService;

    private HangmanGameContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        context = new HangmanGameContext(
                5,
                makeWord("hello"),
                new Hangman(new char[28]),
                makeWord(""),
                makeWord("")
        );

        hangmanGameService = new HangmanGameServiceImpl(
                context,
                reader,
                contextService,
                stringPrinter,
                render,
                inputConverter,
                inputValidator
        );
    }

    @Test
    void testMoveWhenInputIsCorrectAndWordIsGuessed() {
        when(contextService.hasAttempt(context)).thenReturn(true);
        when(reader.read()).thenReturn("t");
        when(inputValidator.isValidLengthOfInput("t")).thenReturn(true);
        when(inputConverter.convertToInput("t")).thenReturn(new Input('t'));
        when(inputConverter.convertToLowerCase(new Input('t'))).thenReturn(new Input('t'));
        when(inputValidator.hasInputAccepted(any(), any())).thenReturn(true);
        when(contextService.updateGuessedLetters(any(), any())).thenReturn(context);
        when(contextService.isWordGuessed(context)).thenReturn(true);

        hangmanGameService.play();

        verify(stringPrinter).println("\nYay, you win!\n");
    }

    @Test
    void testMoveWhenInputHasNotAcceptedAndAttemptsAreDecreased() {
        when(contextService.hasAttempt(context)).thenReturn(true).thenReturn(false);
        when(reader.read()).thenReturn("t");
        when(inputValidator.isValidLengthOfInput("t")).thenReturn(true);
        when(inputConverter.convertToInput("t")).thenReturn(new Input('t'));
        when(inputConverter.convertToLowerCase(new Input('t'))).thenReturn(new Input('t'));
        when(inputValidator.hasInputAccepted(any(), any())).thenReturn(false);
        when(contextService.decreaseAttempts(any())).thenReturn(context);
        when(contextService.addNewPartOfHangman(any())).thenReturn(context);

        hangmanGameService.play();

        verify(contextService).decreaseAttempts(context);
        verify(contextService).addNewPartOfHangman(context);
    }

    @Test
    void testMoveWhenNoAttemptsLeft() {
        when(contextService.hasAttempt(context)).thenReturn(false);

        hangmanGameService.play();

        verify(stringPrinter).println("\nYou lost :(\n");
    }

    @Test
    void testMoveWhenInputIsInvalidLengthThenNoAttemptsDecreasedAndWarningDisplayed() {
        when(contextService.hasAttempt(context)).thenReturn(true);
        when(reader.read()).thenReturn("miss").thenReturn("a");
        when(inputValidator.isValidLengthOfInput("miss")).thenReturn(false);
        when(inputValidator.isValidLengthOfInput("a")).thenReturn(true);
        when(inputConverter.convertToInput("a")).thenReturn(new Input('a'));
        when(inputConverter.convertToLowerCase(new Input('a'))).thenReturn(new Input('a'));
        when(inputValidator.hasInputAccepted(any(), any())).thenReturn(true);

        hangmanGameService.play();

        verify(stringPrinter).println("enter only one letter");
        verify(contextService, never()).decreaseAttempts(context);
        verify(contextService).updateGuessedLetters(any(), any());
    }

    private Word makeWord(String string) {
        return new Word(string.toCharArray());
    }
}
