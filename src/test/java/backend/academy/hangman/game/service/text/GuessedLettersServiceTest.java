package backend.academy.hangman.game.service.text;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.text.impl.GuessedLettersServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GuessedLettersServiceTest {
    private final GuessedLettersService guessedLettersService = new GuessedLettersServiceImpl();

    private static Stream<Arguments> wordUpdateProvider() {
        return Stream.of(
                Arguments.of(3, "ca", 't', new char[]{'c', 'a', 't'}),
                Arguments.of(3, "tes", 't', new char[]{'t', 'e', 's'}),
                Arguments.of(5, "h", 'h', new char[]{'h', '\0', '\0', '\0', '\0'})
        );
    }

    @ParameterizedTest
    @MethodSource("wordUpdateProvider")
    void testUpdateWord(int length, String inputString, char inputChar, char[] expectedChars) {
        Word word = createWordWithLengthFromString(length, inputString);
        Input input = new Input(inputChar);

        Word updatedWord = guessedLettersService.updateWord(word, input);

        assertThat(updatedWord.text()).containsExactly(expectedChars);
    }

    private Word createWordWithLengthFromString(int length, String input) {
        var word = new char[length];

        for (int i = 0; i < input.length(); i++) {
            word[i] = input.charAt(i);
        }

        return new Word(word);
    }
}
