package backend.academy.hangman.game.services.hangman;

import backend.academy.hangman.game.models.Hangman;
import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.CharacterInputValidator;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.InputConverter;
import backend.academy.hangman.game.services.StringInputConverter;
import backend.academy.hangman.game.services.StringInputLengthValidator;
import backend.academy.hangman.game.services.StringPrinter;
import backend.academy.hangman.game.services.StringReader;
import backend.academy.hangman.game.services.StringRender;
import backend.academy.hangman.game.services.impl.hangman.HangmanGameServiceImpl;
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
    private CharacterInputValidator inputValidator;

    @Mock
    private HangmanContextService contextService;

    @Mock
    private StringPrinter stringPrinter;

    @Mock
    private StringRender hangmanRender;

    @Mock
    private StringRender attemptsRender;

    @Mock
    private StringRender guessedLettersRender;

    @Mock
    private InputConverter inputConverter;

    @Mock
    private StringInputConverter stringInputConverter;

    @Mock
    private StringInputLengthValidator stringInputLengthValidator;

    @InjectMocks
    private HangmanGameServiceImpl hangmanGameService;

    private HangmanGameContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        context = new HangmanGameContext(5, makeWord("hello"), new Hangman(new char[28]), makeWord(""));

        hangmanGameService = new HangmanGameServiceImpl(
            context,
            reader,
            inputValidator,
            contextService,
            stringPrinter,
            hangmanRender,
            attemptsRender,
            guessedLettersRender,
            inputConverter,
            stringInputConverter,
            stringInputLengthValidator
        );
    }

    @Test
    void testMoveWhenInputIsCorrectAndWordIsGuessed() {
        when(contextService
            .hasAttempt(context))
            .thenReturn(true);
        when(reader
            .read())
            .thenReturn("t");
        when(stringInputLengthValidator
            .isValid("t"))
            .thenReturn(true);
        when(stringInputConverter
            .convert("t"))
            .thenReturn(new Input('t'));
        when(inputConverter
            .convert(new Input('t')))
            .thenReturn(new Input('t'));
        when(inputValidator
            .hasInputAccepted(any(), any()))
            .thenReturn(true);
        when(contextService
            .updateGuessedLetters(any(), any()))
            .thenReturn(context);
        when(contextService
            .isWordGuessed(context))
            .thenReturn(true);

        hangmanGameService.move();

        verify(stringPrinter).println("\nYay, you win!\n");
    }

    @Test
    void testMoveWhenInputHasNotAcceptedAndAttemptsAreDecreased() {
        when(contextService
            .hasAttempt(context))
            .thenReturn(true)
            .thenReturn(false);
        when(reader
            .read())
            .thenReturn("t");
        when(stringInputLengthValidator
            .isValid("t"))
            .thenReturn(true);
        when(stringInputConverter
            .convert("t"))
            .thenReturn(new Input('t'));
        when(inputConverter
            .convert(new Input('t')))
            .thenReturn(new Input('t'));
        when(inputValidator
            .hasInputAccepted(any(), any()))
            .thenReturn(false);
        when(contextService
            .decreaseAttempts(any()))
            .thenReturn(context);
        when(contextService
            .addNewPartOfHangman(any()))
            .thenReturn(context);

        hangmanGameService.move();

        verify(contextService).decreaseAttempts(context);
        verify(contextService).addNewPartOfHangman(context);
    }

    @Test
    void testMoveWhenNoAttemptsLeft() {
        when(contextService
            .hasAttempt(context))
            .thenReturn(false);

        hangmanGameService.move();

        verify(stringPrinter).println("\nYou lost :(\n");
    }

    @Test
    void testMoveWhenInputIsInvalidLengthThenNoAttemptsDecreasedAndWarningDisplayed() {
        when(contextService
            .hasAttempt(context))
            .thenReturn(true);
        when(reader.read())
            .thenReturn("miss")
            .thenReturn("a");
        when(stringInputLengthValidator
            .isValid("miss"))
            .thenReturn(false);
        when(stringInputLengthValidator
            .isValid("a"))
            .thenReturn(true);
        when(stringInputConverter
            .convert("a"))
            .thenReturn(new Input('a'));
        when(inputConverter
            .convert(new Input('a')))
            .thenReturn(new Input('a'));
        when(inputValidator
            .hasInputAccepted(any(), any()))
            .thenReturn(true);

        hangmanGameService.move();

        verify(stringPrinter).println("enter only one letter");

        verify(contextService, never()).decreaseAttempts(context);

        verify(contextService).updateGuessedLetters(any(), any());
    }

    private Word makeWord(String string) {
        return new Word(string.toCharArray());
    }
}
