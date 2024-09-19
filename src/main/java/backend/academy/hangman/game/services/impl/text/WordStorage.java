package backend.academy.hangman.game.services.impl.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class WordStorage {

    public static String getRandomWord(String category, String difficulty) throws IOException {
        String fileName = category.toLowerCase() + "_" + difficulty.toLowerCase() + ".txt";
        List<String> wordList = readWordsFromFile(fileName);
        Random random = new Random();
        String line = wordList.get(random.nextInt(wordList.size()));
        return line.split(" - ")[0].trim();
    }

    public static String getHintForWord(String word, String category, String difficulty) throws IOException {
        String fileName = category.toLowerCase() + "_" + difficulty.toLowerCase() + ".txt";
        List<String> wordList = readWordsFromFile(fileName);

        Optional<String> lineWithHint = wordList.stream()
            .filter(line -> line.startsWith(word + " -"))
            .findFirst();

        if (lineWithHint.isPresent()) {
            String[] parts = lineWithHint.get().split(" - ");
            if (parts.length == 2) {
                return parts[1].trim();
            }
        }

        throw new IOException("Hint not found for word: " + word);
    }

    private static List<String> readWordsFromFile(String fileName) throws IOException {
        ClassLoader classLoader = WordStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("words/" + fileName);

        if (inputStream == null) {
            throw new IOException("File not found: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}
