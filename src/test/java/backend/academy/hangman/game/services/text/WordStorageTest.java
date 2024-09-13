package backend.academy.hangman.game.services.text;

import backend.academy.hangman.game.services.impl.text.WordStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Files.write(TEST_DIRECTORY.resolve("category_difficulty.txt"), List.of("example", "test", "word"));
    }

    @Test
    void testGetRandomWordWhenFileExistsThenReturnsWord() throws IOException {
        Files.createDirectories(TEST_DIRECTORY);

        String result = WordStorage.getRandomWord("category", "difficulty");
        assertThat(List.of("example", "test", "word")).contains(result);
    }

    @Test
    void testGetRandomWordWhenFileDoesNotExistThenThrowsIOException() {
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream("words/category_difficulty.txt")).thenReturn(null);

        assertThrows(IOException.class, () -> WordStorage.getRandomWord("cat", "difficulty"));
    }
}
