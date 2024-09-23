package backend.academy.hangman.game.service.hangman;

import backend.academy.hangman.game.model.Hangman;
import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.hangman.impl.HangmanStateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanStateServiceTest {
    private HangmanStateServiceImpl hangmanStateService;
    private HangmanGameContext context;

    @BeforeEach
    void setUp() {
        var initialHangmanState = new char[28];
        context = new HangmanGameContext(
                5,
                makeWord("test1"),
                new Hangman(initialHangmanState),
                makeWord(""),
                makeWord("")
        );

        hangmanStateService = new HangmanStateServiceImpl();
    }

    @Test
    void testAddPartWhenMistakesLessThan10ThenEqualsAdded() {
        setUpPartsFrom0To(9);

        char[] expectedHangmanState = new char[28];
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 0, 9, '=');

        assertThat(context.hangman().hangman()).isEqualTo(expectedHangmanState);
    }

    @Test
    void testAddPartWhenMistakesBetween10And15ThenVerticalLineAdded() {
        setUpPartsFrom0To(15);

        char[] expectedHangmanState = new char[28];
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 0, 9, '=');
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 9, 15, '|');

        assertThat(context.hangman().hangman()).isEqualTo(expectedHangmanState);
    }

    @Test
    void testAddPartWhenMistakesBetween16And19ThenDashAdded() {
        setUpPartsFrom0To(19);

        char[] expectedHangmanState = new char[28];
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 0, 9, '=');
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 9, 15, '|');
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 15, 19, '-');

        assertThat(context.hangman().hangman()).isEqualTo(expectedHangmanState);
    }

    @Test
    void testAddPartWhenMistakesGreaterThan19ThenSpecificPartsAdded() {
        setUpPartsFrom0To(26);

        char[] expectedHangmanState = new char[28];
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 0, 9, '=');
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 9, 15, '|');
        expectedHangmanState = setUpExpectedStateFromToAndWithChar(expectedHangmanState, 15, 19, '-');
        expectedHangmanState[19] = '|';
        expectedHangmanState[20] = 'O';
        expectedHangmanState[21] = '|';
        expectedHangmanState[22] = '/';
        expectedHangmanState[23] = '\\';
        expectedHangmanState[24] = '/';
        expectedHangmanState[25] = '\\';

        assertThat(context.hangman().hangman()).isEqualTo(expectedHangmanState);
    }

    private Word makeWord(String string) {
        return new Word(string.toCharArray());
    }

    private void setUpPartsFrom0To(int to) {
        for (int i = 0; i < to; i++) {
            context = hangmanStateService.addPart(context);
        }
    }

    private char[] setUpExpectedStateFromToAndWithChar(char[] expectedState, int from, int to, char c) {
        char[] expectedHangmanState = expectedState;

        for (int i = from; i < to; i++) {
            expectedHangmanState[i] = c;
        }

        return expectedHangmanState;
    }
}
