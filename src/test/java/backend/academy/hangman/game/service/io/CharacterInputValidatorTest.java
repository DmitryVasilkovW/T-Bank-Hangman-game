package backend.academy.hangman.game.service.io;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.io.impl.CharacterInputValidatorImpl;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterInputValidatorTest {
    private final CharacterInputValidatorImpl validator = new CharacterInputValidatorImpl();;

    @Test
    void testHasInputAcceptedWhenCharIsInWordThenReturnsTrue() {
        Word word = new Word("hello".toCharArray());
        Input input = new Input('e');

        boolean result = validator.hasInputAccepted(input, word);

        assertThat(result).isTrue();
    }

    @Test
    void testHasInputAcceptedWhenCharIsNotInWordThenReturnsFalse() {
        Word word = new Word("test".toCharArray());
        Input input = new Input('z');

        boolean result = validator.hasInputAccepted(input, word);

        assertThat(result).isFalse();
    }

    @Test
    void testHasInputAcceptedWhenWordIsEmptyThenReturnsFalse() {
        Word emptyWord = new Word("".toCharArray());
        Input input = new Input('a');

        boolean result = validator.hasInputAccepted(input, emptyWord);

        assertThat(result).isFalse();
    }

    @Test
    void testHasInputAcceptedWhenCharIsRepeatedInWordThenReturnsTrue() {
        Word word = new Word("balloon".toCharArray());
        Input input = new Input('l');

        boolean result = validator.hasInputAccepted(input, word);

        assertThat(result).isTrue();
    }

    @Test
    void testHasInputAcceptedWhenCharIsFirstInWordThenReturnsTrue() {
        Word word = new Word("hello".toCharArray());
        Input input = new Input('h');

        boolean result = validator.hasInputAccepted(input, word);

        assertThat(result).isTrue();
    }

    @Test
    void testHasInputAcceptedWhenCharIsLastInWordThenReturnsTrue() {
        Word word = new Word("hello".toCharArray());
        Input input = new Input('o');

        boolean result = validator.hasInputAccepted(input, word);

        assertThat(result).isTrue();
    }
}
