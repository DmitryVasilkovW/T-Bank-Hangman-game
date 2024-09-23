package backend.academy.hangman.game.service.hangman.impl;

import backend.academy.hangman.game.exception.AttemptsAreNotSetException;
import backend.academy.hangman.game.exception.CategoryIsNotSetException;
import backend.academy.hangman.game.exception.DifficultyIsNotSetException;
import backend.academy.hangman.game.exception.HintNotFoundException;
import backend.academy.hangman.game.exception.IncorrectAttemptsException;
import backend.academy.hangman.game.exception.IncorrectCategoryException;
import backend.academy.hangman.game.exception.IncorrectDifficultyException;
import backend.academy.hangman.game.service.hangman.HangmanGameSettingsInputValidator;
import backend.academy.hangman.game.service.hangman.RandomSetter;
import backend.academy.hangman.game.service.io.StringPrinter;
import backend.academy.hangman.game.service.io.StringReader;
import backend.academy.hangman.game.service.text.InputConverter;
import backend.academy.hangman.game.service.text.impl.WordMetaLoader;
import backend.academy.hangman.game.service.text.impl.WordStorage;
import java.io.IOException;
import java.util.Set;

public class GameLauncher {

    private final HangmanGameSettingsInputValidator validator;
    private final StringPrinter printer;
    private final StringReader reader;
    private final RandomSetter randomSetter;
    private final InputConverter converter;
    private static final String WORDS_OF_FAILURE = "Something went wrong";
    private static final int DEFAULT_AMOUNT_OF_ATTEMPTS = 6;

    public GameLauncher(
            HangmanGameSettingsInputValidator validator,
            StringPrinter printer,
            StringReader reader,
            RandomSetter randomSetter,
            InputConverter converter
    ) {
        this.validator = validator;
        this.printer = printer;
        this.reader = reader;
        this.randomSetter = randomSetter;
        this.converter = converter;
    }

    public void launchGame() {
        WordMetaLoader loader;

        try {
            loader = new WordMetaLoader();
        } catch (Exception e) {
            printer.println(WORDS_OF_FAILURE);
            return;
        }

        String category = getCategory(loader);
        String difficulty = getDifficulty(loader, category);
        int attempts = getAttempts();

        tryToStartGame(category, difficulty, attempts);
    }

    private void tryToStartGame(String category, String difficulty, int attempts) {
        try {
            String word = WordStorage.getRandomWord(category, difficulty);
            String hint = WordStorage.getHintForWord(word, category, difficulty);

            printer.println("The word has been chosen, let the game begin!\n");
            printer.println("Category is " + category + " of " + difficulty + " complexity");
            printer.println("Enter 1 to get hint");
            var game = new CLIHangmanGameFactoryImpl(new HangmanContextFactoryImpl())
                    .createHangmanGame(attempts, word, hint);

            game.play();
        } catch (HintNotFoundException | IOException e) {
            printer.println(WORDS_OF_FAILURE);
        }
    }

    private String getCategory(WordMetaLoader loader) {
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

    private String getDifficulty(WordMetaLoader loader, String category) {
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

    private int getAttempts() {
        int attempts = DEFAULT_AMOUNT_OF_ATTEMPTS;
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
                attempts = randomSetter.getRandomAttemptsBetweenSixAndTwentySix();
                isValid = true;
            }
        }

        return attempts;
    }
}