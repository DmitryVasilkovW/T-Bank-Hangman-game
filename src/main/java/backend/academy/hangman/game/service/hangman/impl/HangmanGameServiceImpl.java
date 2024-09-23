package backend.academy.hangman.game.service.hangman.impl;

import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.service.hangman.HangmanContextService;
import backend.academy.hangman.game.service.hangman.HangmanGameService;
import backend.academy.hangman.game.service.io.HangmanGameInputConverter;
import backend.academy.hangman.game.service.io.HangmanGameInputValidator;
import backend.academy.hangman.game.service.io.StringPrinter;
import backend.academy.hangman.game.service.io.StringReader;
import backend.academy.hangman.game.service.text.StringHangmanGameContextRender;


public class HangmanGameServiceImpl implements HangmanGameService {

    private HangmanGameContext context;
    private final StringReader reader;
    private final HangmanContextService contextService;
    private final StringPrinter stringPrinter;
    private final StringHangmanGameContextRender render;
    private final HangmanGameInputConverter converter;
    private final HangmanGameInputValidator validator;
    private boolean isWordGuessed = false;
    private boolean whetherToShowHint = false;
    private String input;

    public HangmanGameServiceImpl(
            HangmanGameContext context,
            StringReader reader,
            HangmanContextService contextService,
            StringPrinter stringPrinter,
            StringHangmanGameContextRender render,
            HangmanGameInputConverter converter,
            HangmanGameInputValidator validator
    ) {
        this.context = context;
        this.reader = reader;
        this.contextService = contextService;
        this.render = render;
        this.stringPrinter = stringPrinter;
        this.converter = converter;
        this.validator = validator;
    }

    public void play() {
        while (contextService.hasAttempt(context) && !isWordGuessed) {
            showContext();

            input = reader.read();
            if (whetherContextWithHintShouldBeShownAgain()) {
                continue;
            }

            Input text = converter.convertToLowerCase(getCorrectInput());
            if (validator.hasInputAccepted(text, context.expectedWord())) {
                context = contextService.updateGuessedLetters(context, text);
                isWordGuessed = contextService.isWordGuessed(context);
            } else {
                context = contextService.decreaseAttempts(context);
                context = contextService.addNewPartOfHangman(context);
            }
        }

        showContext();
        showEndGameWords();
    }

    private Input getCorrectInput() {
        boolean isCorrect = validator.isValidLengthOfInput(input);

        while (!isCorrect) {
            stringPrinter.println("enter only one letter");
            input = reader.read();

            isCorrect = validator.isValidLengthOfInput(input);
        }

        return converter.convertToInput(input);
    }

    private boolean whetherContextWithHintShouldBeShownAgain() {
        if (validator.isInputValueOfHintButton(input)) {
            whetherToShowHint = true;
            return true;
        }

        return false;
    }

    private void showContext() {
        if (whetherToShowHint) {
            stringPrinter.println(render.renderHint(context));
            stringPrinter.println("\n");
        }
        stringPrinter.println(render.renderHangman(context));
        stringPrinter.println(render.renderAttempts(context));
        stringPrinter.println(render.renderGuessedLetters(context));
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
