package backend.academy;

import backend.academy.hangman.game.services.impl.hangman.CLIHangmanGameFactoryImpl;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIGameSetupReaderImpl;
import backend.academy.hangman.game.services.impl.text.WordMetaLoader;
import backend.academy.hangman.game.services.impl.text.WordStorage;
import java.io.IOException;
import java.net.URISyntaxException;

public class CLIHangmanGame {

    public static void main(String[] args) {
        var printer = new CLISpringPrinterImpl();
        var reader = new ScannerCLIGameSetupReaderImpl();
        WordMetaLoader loader;

        try {
            loader = new WordMetaLoader();
        } catch (IOException e) {
            System.out.println(":(");
            return;
        } catch (URISyntaxException e) {
            System.out.println(":(");
            return;
        }

        String categories = loader.getCategoriesAsString();
        printer.println("Choose a category: " + categories);
        String category = reader.readSettingForWord();

        String difficulties = loader.getDifficultiesAsString(category);
        printer.println("Choose difficulty: " + difficulties);
        String difficulty = reader.readSettingForWord();

        printer.println("Choose the number of attempts from 1 to 26");
        int attempts = reader.readAttempts();

        try {
            String word = WordStorage.getRandomWord(category, difficulty);
            printer.println("The word has been chosen, let the game begin!\n");
            var game = new CLIHangmanGameFactoryImpl().createHangmanGame(attempts, word);
            game.move();
        } catch (IOException e) {
            System.out.println(":(");
        }
    }
}
