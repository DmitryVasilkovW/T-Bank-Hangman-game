package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.services.HangmanContextService;
import backend.academy.hangman.game.services.HangmanGameService;
import backend.academy.hangman.game.services.InputReader;
import backend.academy.hangman.game.services.InputValidator;
import backend.academy.hangman.game.services.StringPrinter;
import backend.academy.hangman.game.services.StringRender;

public class HangmanGameServiceImpl implements HangmanGameService {
    private HangmanGameContext context;
    private final InputReader reader;
    private final InputValidator inputValidator;
    private final HangmanContextService contextService;
    private final StringRender stringRender;
    private final StringPrinter stringPrinter;

    public HangmanGameServiceImpl(
        HangmanGameContext context,
        InputReader reader,
        InputValidator inputValidator,
        HangmanContextService contextService,
        StringRender stringRender, StringPrinter stringPrinter
    ) {

        this.context = context;
        this.reader = reader;
        this.inputValidator = inputValidator;
        this.contextService = contextService;
        this.stringRender = stringRender;
        this.stringPrinter = stringPrinter;
    }

    public void move() {
        while (contextService.hasAttempt(context)) {
            Input text = reader.read();

            if (!inputValidator.hasInputAccepted(text, context.expectedWord())) {
                context = contextService.decreaseAttempts(context);
                context = contextService.addNewPartOfHangman(context);

                stringPrinter.print(stringRender.render(context));
            }
        }
    }
}
