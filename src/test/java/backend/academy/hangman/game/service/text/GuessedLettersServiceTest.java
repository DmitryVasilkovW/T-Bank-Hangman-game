package backend.academy.hangman.game.service.text;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.text.impl.GuessedLettersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GuessedLettersServiceTest {
    private GuessedLettersService guessedLettersService;

    @BeforeEach
    void setUp() {
        guessedLettersService = new GuessedLettersServiceImpl();
    }

    @Test
    void testUpdateWordWhenLetterIsNotInWordThenUpdatesWordWithInput() {
        Word word = createWordWithLengthFromString(3, "ca");
        Input input = new Input('t');

        Word updatedWord = guessedLettersService.updateWord(word, input);

        assertThat(updatedWord.text()).containsExactly('c', 'a', 't');
    }

    @Test
    void testUpdateWordWhenLetterIsInWordThenUpdatesWordCorrectly() {
        Word word = createWordWithLengthFromString(3, "tes");
        Input input = new Input('t');

        Word updatedWord = guessedLettersService.updateWord(word, input);

        assertThat(updatedWord.text()).containsExactly('t', 'e', 's');
    }

    @Test
    void testUpdateWordWhenLetterIsAlreadyInTheWordThenUpdatesWordCorrectly() {
        Word word = createWordWithLengthFromString(5, "h");
        Input input = new Input('h');

        Word updatedWord = guessedLettersService.updateWord(word, input);

        assertThat(updatedWord.text()).containsExactly('h', '\0', '\0', '\0', '\0');
    }

    private Word createWordWithLengthFromString(int length, String input) {
        var word = new char[length];

        for (int i = 0; i < input.length(); i++) {
            word[i] = input.charAt(i);
        }

        return new Word(word);
    }
}
