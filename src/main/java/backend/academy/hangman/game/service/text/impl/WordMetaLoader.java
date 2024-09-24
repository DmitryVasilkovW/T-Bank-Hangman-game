package backend.academy.hangman.game.service.text.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static backend.academy.hangman.game.service.text.impl.WordSourceProperties.BASE_PATH;
import static backend.academy.hangman.game.service.text.impl.WordSourceProperties.FILE_EXTENSION;

public class WordMetaLoader {

    private final Map<String, Set<String>> categoryDifficultyMap = new HashMap<>();

    public WordMetaLoader() throws IOException, URISyntaxException {
        loadCategoriesAndDifficulties();
    }

    private void loadCategoriesAndDifficulties() throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        Path path = Paths.get(classLoader.getResource(BASE_PATH).toURI());

        Files.list(path)
            .filter(Files::isRegularFile)
            .forEach(file -> {
                String fileName = file.getFileName().toString();
                if (fileName.endsWith(FILE_EXTENSION)) {
                    String[] parts = fileName.replace(FILE_EXTENSION, "").split("_");

                    if (parts.length == 2) {
                        String category = parts[0];
                        String difficulty = parts[1];

                        categoryDifficultyMap.computeIfAbsent(category, k -> new java.util.HashSet<>()).add(difficulty);
                    }
                }
            });
    }

    public String getCategoriesAsString() {
        Set<String> categories = getCategoriesAsSet();

        return fillStringWithContent(categories);
    }

    public Set<String> getCategoriesAsSet() {
        return categoryDifficultyMap.keySet();
    }

    public String getDifficultiesAsString(String category) {
        Set<String> difficulties = getDifficultiesAsSet(category);

        return fillStringWithContent(difficulties);
    }

    private String fillStringWithContent(Set<String> content) {
        var sb = new StringBuilder();

        for (String word : content) {
            sb
                .append(word)
                .append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());

        return sb.toString();
    }

    public Set<String> getDifficultiesAsSet(String category) {
        return categoryDifficultyMap.getOrDefault(category, Set.of());
    }
}
