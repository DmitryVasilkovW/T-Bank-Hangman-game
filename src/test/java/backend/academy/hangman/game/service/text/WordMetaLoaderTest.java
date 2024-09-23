package backend.academy.hangman.game.service.text;

import backend.academy.hangman.game.service.text.impl.WordMetaLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WordMetaLoaderTest {
    private WordMetaLoader wordMetaLoader;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        ClassLoader classLoader = Mockito.mock(ClassLoader.class);
        Path mockPath = Paths.get("src/test/resources/words");

        when(classLoader.getResource("words")).thenReturn(mockPath.toUri().toURL());

        wordMetaLoader = new WordMetaLoader();
    }

    @Test
    void testLoadCategoriesAndDifficulties() throws IOException, URISyntaxException {
        Files.createDirectories(Paths.get("src/test/resources/words"));
        Files.write(Paths.get("src/test/resources/words/animals_easy.txt"), new byte[0]);
        Files.write(Paths.get("src/test/resources/words/animals_hard.txt"), new byte[0]);
        Files.write(Paths.get("src/test/resources/words/animals_medium.txt"), new byte[0]);

        wordMetaLoader = new WordMetaLoader();
        Set<String> expectedDifficulties = Set.of("hard", "medium", "easy");

        assertThat(wordMetaLoader.getCategoriesAsSet()).contains("animals");
        assertThat(wordMetaLoader.getDifficultiesAsSet("animals")).isEqualTo(expectedDifficulties);
        assertThat(wordMetaLoader.getCategoriesAsString()).contains("animals");
        assertThat(wordMetaLoader.getDifficultiesAsString("animals")).isEqualTo("hard, medium, easy");
    }
}
