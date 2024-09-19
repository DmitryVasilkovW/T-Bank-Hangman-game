package backend.academy;

import backend.academy.hangman.game.myExceptions.AttemptsAreNotSetException;
import backend.academy.hangman.game.myExceptions.CategoryIsNotSetException;
import backend.academy.hangman.game.myExceptions.DifficultyIsNotSetException;
import backend.academy.hangman.game.myExceptions.IncorrectAttemptsException;
import backend.academy.hangman.game.myExceptions.IncorrectCategoryException;
import backend.academy.hangman.game.myExceptions.IncorrectDifficultyException;
import backend.academy.hangman.game.services.HangmanGameSettingsInputValidator;
import backend.academy.hangman.game.services.impl.hangman.CLIHangmanGameFactoryImpl;
import backend.academy.hangman.game.services.impl.hangman.HangmanGameSettingsInputValidatorImpl;
import backend.academy.hangman.game.services.impl.hangman.RandomSetterImpl;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIReaderImlp;
import backend.academy.hangman.game.services.impl.text.StringLowerCaseConverterImpl;
import backend.academy.hangman.game.services.impl.text.WordMetaLoader;
import backend.academy.hangman.game.services.impl.text.WordStorage;
import java.io.IOException;
import java.util.Set;

public class CLIHangmanGame {
    private static final HangmanGameSettingsInputValidator validator = new HangmanGameSettingsInputValidatorImpl();
    private static final CLISpringPrinterImpl printer = new CLISpringPrinterImpl();
    private static final ScannerCLIReaderImlp reader = new ScannerCLIReaderImlp();
    private static final RandomSetterImpl randomSetter = new RandomSetterImpl();
    private static final StringLowerCaseConverterImpl converter = new StringLowerCaseConverterImpl();

    public static void main(String[] args) {
        WordMetaLoader loader;

        try {
            loader = new WordMetaLoader();
        } catch (Exception e) {
            printer.println("Something went wrong");
            return;
        }

        String category = getCategory(loader);
        String difficulty = getDifficulty(loader, category);
        int attempts = getAttempts();

        tryToStartGame(category, difficulty, attempts);
    }

    private static void tryToStartGame(String category, String difficulty, int attempts) {
        try {
            String word = WordStorage.getRandomWord(category, difficulty);
            String hint = WordStorage.getHintForWord(word, category, difficulty);

            printer.println("The word has been chosen, let the game begin!\n");
            printer.println("Category is " + category + " of " + difficulty + " complexity");
            printer.println("Enter 1 to get hint");
            var game = new CLIHangmanGameFactoryImpl().createHangmanGame(attempts, word, hint);

            game.play();
        } catch (IOException e) {
            printer.println("Something went wrong");
        }
    }

    private static String getCategory(WordMetaLoader loader) {
        String categories = loader.getCategoriesAsString();
        Set<String> setOfCategories = loader.getCategoriesAsSet();
        String category = setOfCategories.iterator().next();
        boolean isValid = false;

        while (!isValid) {
            printer.println("Choose a category: " + categories);
            category = converter.convert(reader.read());

            try {
                validator.validateCategory(category, setOfCategories);
                isValid = true;
            } catch (IncorrectCategoryException e) {
                printer.println(e.getMessage());
            } catch (CategoryIsNotSetException e) {
                category = randomSetter.getRandomCategory(setOfCategories);
                isValid = true;
            }
        }

        return category;
    }

    private static String getDifficulty(WordMetaLoader loader, String category) {
        String difficulties = loader.getDifficultiesAsString(category);
        Set<String> setOfDifficulties = loader.getDifficultiesAsSet(category);
        String difficulty = setOfDifficulties.iterator().next();
        boolean isValid = false;

        while (!isValid) {
            printer.println("Choose difficulty: " + difficulties);
            difficulty = converter.convert(reader.read());

            try {
                validator.validateDifficulty(difficulty, setOfDifficulties);
                isValid = true;
            } catch (IncorrectDifficultyException e) {
                printer.println(e.getMessage());
            } catch (DifficultyIsNotSetException e) {
                difficulty = randomSetter.getRandomDifficulty(setOfDifficulties);
                isValid = true;
            }
        }

        return difficulty;
    }

    private static int getAttempts() {
        int attempts = 6;
        boolean isValid = false;

        while (!isValid) {
            printer.println("Choose the number of attempts from 1 to 26");
            String stringAttempts = reader.read();

            try {
                validator.validateAttempts(stringAttempts);
                attempts = Integer.parseInt(stringAttempts);
                isValid = true;
            } catch (IncorrectAttemptsException e) {
                printer.println(e.getMessage());
            } catch (AttemptsAreNotSetException e) {
                attempts = randomSetter.getRandomAttempts();
                isValid = true;
            }
        }

        return attempts;
    }
}
