package backend.academy;

import backend.academy.hangman.game.myExceptions.AttemptsAreNotSetException;
import backend.academy.hangman.game.myExceptions.CategoryIsNotSetException;
import backend.academy.hangman.game.myExceptions.DifficultyIsNotSetException;
import backend.academy.hangman.game.myExceptions.IncorrectAttemptsException;
import backend.academy.hangman.game.myExceptions.IncorrectCategoryException;
import backend.academy.hangman.game.myExceptions.IncorrectDifficultyException;
import backend.academy.hangman.game.services.impl.hangman.CLIHangmanGameFactoryImpl;
import backend.academy.hangman.game.services.impl.hangman.HangmanGameSettingsInputValidatorImpl;
import backend.academy.hangman.game.services.impl.hangman.RandomSetterImpl;
import backend.academy.hangman.game.services.impl.io.CLISpringPrinterImpl;
import backend.academy.hangman.game.services.impl.io.ScannerCLIGameSetupReaderImpl;
import backend.academy.hangman.game.services.impl.text.WordMetaLoader;
import backend.academy.hangman.game.services.impl.text.WordStorage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

public class CLIHangmanGame {

    public static void main(String[] args) {
        var printer = new CLISpringPrinterImpl();
        var reader = new ScannerCLIGameSetupReaderImpl();
        var validator = new HangmanGameSettingsInputValidatorImpl();
        var randomSetter = new RandomSetterImpl();
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
        Set<String> setOfCategories = loader.getCategoriesAsSet();
        printer.println("Choose a category: " + categories);
        String category = reader.readSettingForWordAsString();

        try {
            validator.validateCategory(category, setOfCategories);
        } catch (IncorrectCategoryException e) {
            printer.println(e.getMessage());
        } catch (CategoryIsNotSetException e) {
            category = randomSetter.getRandomCategory(setOfCategories);
        }

        String difficulties = loader.getDifficultiesAsString(category);
        Set<String> setOfDifficulties = loader.getDifficultiesAsSet(category);
        printer.println("Choose difficulty: " + difficulties);
        String difficulty = reader.readSettingForWordAsString();

        try {
            validator.validateDifficulty(difficulty, setOfDifficulties);
        } catch (IncorrectDifficultyException e) {
            printer.println(e.getMessage());
        } catch (DifficultyIsNotSetException e) {
            difficulty = randomSetter.getRandomDifficulty(setOfDifficulties);
        }

        printer.println("Choose the number of attempts from 1 to 26");
        String stringAttempts = reader.readSettingForWordAsString();
        int attempts = 6;

        try {
            validator.validateAttempts(stringAttempts);
            attempts = Integer.parseInt(stringAttempts);
        } catch (IncorrectAttemptsException e) {
            printer.println(e.getMessage());
        } catch (AttemptsAreNotSetException e) {
            attempts = randomSetter.getRandomAttempts();
        }

        try {
            String word = WordStorage.getRandomWord(category, difficulty);
            printer.println("The word has been chosen, let the game begin!\n");
            printer.println("category is " + category + " of " + difficulty + " complexity ");
            var game = new CLIHangmanGameFactoryImpl().createHangmanGame(attempts, word);

            game.move();
        } catch (IOException e) {
            System.out.println(":(");
        }
    }
}
