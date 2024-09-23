package backend.academy;

import backend.academy.hangman.game.exception.AttemptsAreNotSetException;
import backend.academy.hangman.game.exception.CategoryIsNotSetException;
import backend.academy.hangman.game.exception.DifficultyIsNotSetException;
import backend.academy.hangman.game.exception.HintNotFoundException;
import backend.academy.hangman.game.exception.IncorrectAttemptsException;
import backend.academy.hangman.game.exception.IncorrectCategoryException;
import backend.academy.hangman.game.exception.IncorrectDifficultyException;
import backend.academy.hangman.game.service.hangman.HangmanGameSettingsInputValidator;
import backend.academy.hangman.game.service.hangman.impl.CLIHangmanGameFactoryImpl;
import backend.academy.hangman.game.service.hangman.impl.HangmanContextFactoryImpl;
import backend.academy.hangman.game.service.hangman.impl.HangmanGameSettingsInputValidatorImpl;
import backend.academy.hangman.game.service.hangman.impl.RandomSetterImpl;
import backend.academy.hangman.game.service.io.impl.CLIStringPrinterImpl;
import backend.academy.hangman.game.service.io.impl.ScannerCLIReaderImlp;
import backend.academy.hangman.game.service.text.impl.InputLowerCaseConverterImpl;
import backend.academy.hangman.game.service.text.impl.WordMetaLoader;
import backend.academy.hangman.game.service.text.impl.WordStorage;
import java.io.IOException;
import java.util.Set;
import lombok.experimental.UtilityClass;



@UtilityClass
public class Main {

    private static final HangmanGameSettingsInputValidator VALIDATOR = new HangmanGameSettingsInputValidatorImpl();
    private static final CLIStringPrinterImpl PRINTER = new CLIStringPrinterImpl();
    private static final ScannerCLIReaderImlp READER = new ScannerCLIReaderImlp();
    private static final RandomSetterImpl RANDOM_SETTER = new RandomSetterImpl();
    private static final InputLowerCaseConverterImpl CONVERTER = new InputLowerCaseConverterImpl();
    private static final String WORDS_OF_FAILURE = "Something went wrong";
    private static final int DEFAULT_AMOUNT_OF_ATTEMPTS = 6;

    public static void main(String[] args) {
        WordMetaLoader loader;

        try {
            loader = new WordMetaLoader();
        } catch (Exception e) {
            PRINTER.println(WORDS_OF_FAILURE);
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

            PRINTER.println("The word has been chosen, let the game begin!\n");
            PRINTER.println("Category is " + category + " of " + difficulty + " complexity");
            PRINTER.println("Enter 1 to get hint");
            var game = new CLIHangmanGameFactoryImpl(new HangmanContextFactoryImpl())
                    .createHangmanGame(attempts, word, hint);

            game.play();
        } catch (HintNotFoundException | IOException e) {
            PRINTER.println(WORDS_OF_FAILURE);
        }
    }

    private static String getCategory(WordMetaLoader loader) {
        String categories = loader.getCategoriesAsString();
        Set<String> setOfCategories = loader.getCategoriesAsSet();
        String category = setOfCategories.iterator().next();
        boolean isValid = false;

        while (!isValid) {
            PRINTER.println("Choose a category: " + categories);
            category = CONVERTER.convert(READER.read());

            try {
                VALIDATOR.validateCategory(category, setOfCategories);
                isValid = true;
            } catch (IncorrectCategoryException e) {
                PRINTER.println(e.getMessage());
            } catch (CategoryIsNotSetException e) {
                category = RANDOM_SETTER.getRandomCategory(setOfCategories);
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
            PRINTER.println("Choose difficulty: " + difficulties);
            difficulty = CONVERTER.convert(READER.read());

            try {
                VALIDATOR.validateDifficulty(difficulty, setOfDifficulties);
                isValid = true;
            } catch (IncorrectDifficultyException e) {
                PRINTER.println(e.getMessage());
            } catch (DifficultyIsNotSetException e) {
                difficulty = RANDOM_SETTER.getRandomDifficulty(setOfDifficulties);
                isValid = true;
            }
        }

        return difficulty;
    }

    private static int getAttempts() {
        int attempts = DEFAULT_AMOUNT_OF_ATTEMPTS;
        boolean isValid = false;

        while (!isValid) {
            PRINTER.println("Choose the number of attempts from 1 to 26");
            String stringAttempts = READER.read();

            try {
                VALIDATOR.validateAttempts(stringAttempts);
                attempts = Integer.parseInt(stringAttempts);
                isValid = true;
            } catch (IncorrectAttemptsException e) {
                PRINTER.println(e.getMessage());
            } catch (AttemptsAreNotSetException e) {
                attempts = RANDOM_SETTER.getRandomAttemptsBetweenSixAndTwentySix();
                isValid = true;
            }
        }

        return attempts;
    }
}
