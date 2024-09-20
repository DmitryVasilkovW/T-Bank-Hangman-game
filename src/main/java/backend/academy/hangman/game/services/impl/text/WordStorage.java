package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.myExceptions.HintNotFoundException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public final class WordStorage {

    private static final String FILE_EXTENSION = ".txt";
    private static final String SEPARATOR_IN_FILENAME = "_";
    private static final String SEPARATOR_IN_FILES = " - ";

    private WordStorage() {
    }

    public static String getRandomWord(String category, String difficulty) throws IOException {
        String fileName = category.toLowerCase() + SEPARATOR_IN_FILENAME + difficulty.toLowerCase() + FILE_EXTENSION;
        List<String> wordList = readWordsFromFile(fileName);
        Random random = new Random();
        String line = wordList.get(random.nextInt(wordList.size()));
        return line.split(SEPARATOR_IN_FILES)[0].trim();
    }

    public static String getHintForWord(String word, String category, String difficulty)
        throws IOException, HintNotFoundException {
        String fileName = category.toLowerCase() + SEPARATOR_IN_FILENAME + difficulty.toLowerCase() + FILE_EXTENSION;
        List<String> wordList = readWordsFromFile(fileName);

        Optional<String> lineWithHint = wordList.stream()
            .filter(line -> line.startsWith(word + " -"))
            .findFirst();

        if (lineWithHint.isPresent()) {
            String[] parts = lineWithHint.get().split(SEPARATOR_IN_FILES);
            if (parts.length == 2) {
                return parts[1].trim();
            }
        }

        throw new HintNotFoundException("Hint not found for word: " + word);
    }

    private static List<String> readWordsFromFile(String fileName) throws IOException {
        ClassLoader classLoader = WordStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("words/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}
