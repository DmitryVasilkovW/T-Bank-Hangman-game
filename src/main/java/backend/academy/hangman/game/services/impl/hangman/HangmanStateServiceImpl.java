package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.models.Hangman;
import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.services.HangmanStateService;

public class HangmanStateServiceImpl implements HangmanStateService {
    private int mistakes;

    public HangmanStateServiceImpl(int mistakes) {
        this.mistakes = mistakes;
    }

    public HangmanStateServiceImpl() {
        this.mistakes = 0;
    }

    public HangmanGameContext addPart(HangmanGameContext context) {
        char[] hangmanState = context.hangman().hangman();

        mistakes++;
        if (mistakes < 10) {
            hangmanState[mistakes - 1] = '=';
            return updateContext(context, hangmanState);
        } else if (mistakes < 16) {
            hangmanState[mistakes - 1] = '|';
            return updateContext(context, hangmanState);
        } else if (mistakes < 20) {
            hangmanState[mistakes - 1] = '-';
            return updateContext(context, hangmanState);
        }

        switch (mistakes) {
            case 20:
                hangmanState[19] = '|';
                break;
            case 21:
                hangmanState[20] = 'O';
                break;
            case 22:
                hangmanState[21] = '|';
                break;
            case 23:
                hangmanState[22] = '/';
                break;
            case 24:
                hangmanState[23] = '\\';
                break;
            case 25:
                hangmanState[24] = '/';
                break;
            case 26:
                hangmanState[25] = '\\';
                break;
        }

        return updateContext(context, hangmanState);
    }

    private HangmanGameContext updateContext(HangmanGameContext context, char[] hangmanState) {
        return new HangmanGameContext(context.attempts(), context.expectedWord(), new Hangman(hangmanState), context.guessedLetters());
    }
}
