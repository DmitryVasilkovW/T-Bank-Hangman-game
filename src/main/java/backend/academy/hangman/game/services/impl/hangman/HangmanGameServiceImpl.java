package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.HangmanGameService;
import backend.academy.hangman.game.services.InputConverter;
import backend.academy.hangman.game.services.InputReader;
import backend.academy.hangman.game.services.CharacterInputValidator;
import backend.academy.hangman.game.services.StringPrinter;
import backend.academy.hangman.game.services.StringRender;

public class HangmanGameServiceImpl implements HangmanGameService {
    private HangmanGameContext context;
    private final InputReader reader;
    private final CharacterInputValidator inputValidator;
    private final HangmanContextService contextService;
    private final StringPrinter stringPrinter;
    private final StringRender hangmanRender;
    private final StringRender attemptsRender;
    private final StringRender guessedLettersRender;
    private final InputConverter inputConverter;

    public HangmanGameServiceImpl(
        HangmanGameContext context,
        InputReader reader,
        CharacterInputValidator inputValidator,
        HangmanContextService contextService,
        StringPrinter stringPrinter,
        StringRender stringRender,
        StringRender contextRender,
        StringRender guessedLettersRender,
        InputConverter inputConverter
    ) {
        this.context = context;
        this.reader = reader;
        this.inputValidator = inputValidator;
        this.contextService = contextService;
        this.hangmanRender = stringRender;
        this.stringPrinter = stringPrinter;
        this.attemptsRender = contextRender;
        this.guessedLettersRender = guessedLettersRender;
        this.inputConverter = inputConverter;
    }

    public void move() {
        boolean isWordGuessed = false;
        showContext();

        while (contextService.hasAttempt(context) && !isWordGuessed) {
            Input text = inputConverter.convert(reader.read());

            if (inputValidator.hasInputAccepted(text, context.expectedWord())) {
                context = contextService.updateGuessedLetters(context, text);
                isWordGuessed = contextService.isWordGuessed(context);
            } else {
                context = contextService.decreaseAttempts(context);
                context = contextService.addNewPartOfHangman(context);
            }

            showContext();
        }

        showEndGameWords(isWordGuessed);
    }

    private void showContext(){
        stringPrinter.println(hangmanRender.render(context));
        stringPrinter.println(attemptsRender.render(context));
        stringPrinter.println(guessedLettersRender.render(context));
    }

    private void showEndGameWords(boolean isWordGuessed) {
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
