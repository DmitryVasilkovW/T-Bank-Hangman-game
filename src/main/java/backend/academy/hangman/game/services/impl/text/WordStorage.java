package backend.academy.hangman.game.services.impl.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordStorage {

    public static String getRandomWord(String category, String difficulty) throws IOException {
        String fileName = category.toLowerCase() + "_" + difficulty.toLowerCase() + ".txt";
        List<String> wordList = readWordsFromFile(fileName);
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
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
