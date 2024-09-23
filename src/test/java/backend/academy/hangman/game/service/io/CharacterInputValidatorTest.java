package backend.academy.hangman.game.service.io;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.io.impl.CharacterInputValidatorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterInputValidatorTest {
    private final CharacterInputValidatorImpl validator = new CharacterInputValidatorImpl();

    private static Stream<Arguments> inputAcceptedProvider() {
        return Stream.of(
                Arguments.of("hello", 'e', true),
                Arguments.of("test", 'z', false),
                Arguments.of("", 'a', false),
                Arguments.of("balloon", 'l', true),
                Arguments.of("hello", 'h', true),
                Arguments.of("hello", 'o', true)
        );
    }

    @ParameterizedTest
    @MethodSource("inputAcceptedProvider")
    void testHasInputAccepted(String wordString, char inputChar, boolean expected) {
        Word word = new Word(wordString.toCharArray());
        Input input = new Input(inputChar);

        boolean result = validator.hasInputAccepted(input, word);

        assertThat(result).isEqualTo(expected);
    }
}
