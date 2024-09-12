package backend.academy;

import backend.academy.hangman.game.services.impl.hangman.CLIHangmanGameFactoryImpl;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIGameSetupReaderImpl;
import backend.academy.hangman.game.services.impl.text.WordStorage;
import java.io.IOException;

public class CLIHangmanGame {

    public static void main(String[] args) {
        var printer = new CLISpringPrinterImpl();
        var reader = new ScannerCLIGameSetupReaderImpl();

        printer.println("Choose a category: Animals, Fruits");
        String category = reader.readSettingForWord();

        printer.println("Choose difficulty: Easy, Medium, Hard");
        String difficulty = reader.readSettingForWord();

        printer.println("Choose the number of attempts from 1 to 26");
        int attempts = reader.readAttempts();

        try {
            String word = WordStorage.getRandomWord(category, difficulty);
            printer.println("The word has been chosen, let the game begin!");
            var game = new CLIHangmanGameFactoryImpl().createHangmanGame(attempts, word);
            game.move();
        } catch (IOException e) {
            System.out.println(":(");
        }
    }
}
