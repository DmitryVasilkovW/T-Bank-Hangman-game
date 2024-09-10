package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.HangmanService;
import backend.academy.hangman.game.services.InputHandler;
import backend.academy.hangman.game.services.InputReader;
import backend.academy.hangman.game.services.InputValidator;
import backend.academy.hangman.game.services.StringRender;

public class HangmanGameServiceImpl implements HangmanService {
    private HangmanGameContext context;
    private final Word expectedWord;
    private final InputHandler inputHandler;
    private final InputReader reader;
    private final InputValidator inputValidator;
    private HangmanContextService contextService;
    private StringRender stringRender;

    public HangmanGameServiceImpl(
        HangmanGameContext context,
        Word expectedWord,
        InputHandler inputHandler,
        InputReader reader,
        InputValidator inputValidator,
        HangmanContextService contextService,
        StringRender stringRender) {

        this.context = context;
        this.expectedWord = expectedWord;
        this.inputHandler = inputHandler;
        this.reader = reader;
        this.inputValidator = inputValidator;
    }

    public void move() {
        while (contextService.hasAttempt(context)) {
            Input text = inputHandler.acceptInput(reader);

            if (!inputValidator.hasInputAccepted(text, expectedWord)) {
                context = contextService.decreaseAttempts(context);
                context = contextService.addNewPartOfHangman(context);

                stringRender.render(context);
            }
        }
    }
}
