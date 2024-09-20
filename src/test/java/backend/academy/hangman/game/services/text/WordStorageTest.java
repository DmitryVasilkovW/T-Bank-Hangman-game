package backend.academy.hangman.game.services.text;

import backend.academy.hangman.game.myExceptions.HintNotFoundException;
import backend.academy.hangman.game.services.impl.text.WordStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WordStorageTest {
    private static final Path TEST_DIRECTORY = Paths.get("src/test/resources/words");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(TEST_DIRECTORY);
        Files.write(TEST_DIRECTORY.resolve("category_difficulty.txt"), List.of(
            "cat - small animal",
            "dog - loyal animal",
            "rat - sneaky animal"
        ));
    }

    @Test
    void testGetRandomWordWhenFileExistsThenReturnsWord() throws IOException {
        Files.createDirectories(TEST_DIRECTORY);

        String result = WordStorage.getRandomWord("category", "difficulty");
        assertThat(List.of("cat", "dog", "rat")).contains(result);
    }

    @Test
    void testGetRandomWordWhenFileDoesNotExistThenThrowsFileNotFoundException() {
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream("words/category_difficulty.txt")).thenReturn(null);

        assertThrows(FileNotFoundException.class, () -> WordStorage.getRandomWord("cat", "difficulty"));
    }

    @Test
    void testGetHintForWordWhenHintExistsThenReturnsHint() throws IOException, HintNotFoundException {
        String hint = WordStorage.getHintForWord("cat", "category", "difficulty");
        assertThat(hint).isEqualTo("small animal");
    }

    @Test
    void testGetHintForWordWhenHintDoesNotExistThenThrowsHintNotFoundException() {
        assertThrows(HintNotFoundException.class, () -> WordStorage.getHintForWord("fox", "category", "difficulty"));
    }

    @Test
    void testGetHintForWordWhenFileDoesNotExistThenThrowsFileNotFoundException() {
        assertThrows(
            FileNotFoundException.class, () -> WordStorage.getHintForWord("cat", "invalid_category", "difficulty"));
    }
}

