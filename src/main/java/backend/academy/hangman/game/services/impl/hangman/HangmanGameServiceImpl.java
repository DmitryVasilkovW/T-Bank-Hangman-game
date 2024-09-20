package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.HangmanGameService;
import backend.academy.hangman.game.services.InputConverter;
import backend.academy.hangman.game.services.CharacterInputValidator;
import backend.academy.hangman.game.services.StringInputConverter;
import backend.academy.hangman.game.services.StringInputValidator;
import backend.academy.hangman.game.services.StringPrinter;
import backend.academy.hangman.game.services.StringReader;
import backend.academy.hangman.game.services.StringRender;

public class HangmanGameServiceImpl implements HangmanGameService {
    private HangmanGameContext context;
    private final StringReader reader;
    private final CharacterInputValidator inputValidator;
    private final HangmanContextService contextService;
    private final StringPrinter stringPrinter;
    private final StringRender hangmanRender;
    private final StringRender attemptsRender;
    private final StringRender guessedLettersRender;
    private final StringRender hintRender;
    private final InputConverter inputConverter;
    private final StringInputConverter stringInputConverter;
    private final StringInputValidator stringInputLengthValidator;
    private final StringInputValidator stringHintInputValidator;
    private boolean isWordGuessed = false;
    private boolean whetherToShowHint = false;
    private String input;

    public HangmanGameServiceImpl(
        HangmanGameContext context,
        StringReader reader,
        CharacterInputValidator inputValidator,
        HangmanContextService contextService,
        StringPrinter stringPrinter,
        StringRender stringRender,
        StringRender contextRender,
        StringRender guessedLettersRender,
        StringRender hintRender,
        InputConverter inputConverter,
        StringInputConverter stringInputConverter,
        StringInputValidator stringInputLengthValidator,
        StringInputValidator stringHintInputValidator
    ) {
        this.context = context;
        this.reader = reader;
        this.inputValidator = inputValidator;
        this.contextService = contextService;
        this.hangmanRender = stringRender;
        this.stringPrinter = stringPrinter;
        this.attemptsRender = contextRender;
        this.guessedLettersRender = guessedLettersRender;
        this.hintRender = hintRender;
        this.inputConverter = inputConverter;
        this.stringInputConverter = stringInputConverter;
        this.stringInputLengthValidator = stringInputLengthValidator;
        this.stringHintInputValidator = stringHintInputValidator;
    }

    public void play() {
        while (contextService.hasAttempt(context) && !isWordGuessed) {
            showContext();

            input = reader.read();
            if (whetherContextWithHintShouldBeShownAgain()) {
                continue;
            }

            Input text = inputConverter.convert(getCorrectInput());

            if (inputValidator.hasInputAccepted(text, context.expectedWord())) {
                context = contextService.updateGuessedLetters(context, text);
                isWordGuessed = contextService.isWordGuessed(context);
            } else {
                context = contextService.decreaseAttempts(context);
                context = contextService.addNewPartOfHangman(context);
            }
        }

        showEndGameWords();
    }

    private Input getCorrectInput() {
        boolean isCorrect = stringInputLengthValidator.isValid(input);

        while (!isCorrect) {
            stringPrinter.println("enter only one letter");
            input = reader.read();

            isCorrect = stringInputLengthValidator.isValid(input);
        }

        return stringInputConverter.convert(input);
    }

    private boolean whetherContextWithHintShouldBeShownAgain() {
        if (stringHintInputValidator.isValid(input)) {
            whetherToShowHint = true;
            return true;
        }

        return false;
    }

    private void showContext() {
        if (whetherToShowHint) {
            stringPrinter.println(hintRender.render(context));
            stringPrinter.println("\n");
        }
        stringPrinter.println(hangmanRender.render(context));
        stringPrinter.println(attemptsRender.render(context));
        stringPrinter.println(guessedLettersRender.render(context));
    }

    private void showEndGameWords() {
        if (isWordGuessed) {
            showVictoryWords();
        } else {
            showDefeatWords();
        }
    }

    private void showVictoryWords() {
        stringPrinter.println("\nYay, you win!\n");
    }

    private void showDefeatWords() {
        stringPrinter.println("\nYou lost :(\n");
    }
}
