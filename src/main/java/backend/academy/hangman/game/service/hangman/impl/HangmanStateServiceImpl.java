package backend.academy.hangman.game.service.hangman.impl;

import backend.academy.hangman.game.model.Hangman;
import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.service.hangman.HangmanStateService;

public class HangmanStateServiceImpl implements HangmanStateService {

    private static final int MAX_HEAD_AND_BODY_PARTS = 9;
    private static final int MAX_TORSO_PARTS = 15;
    private static final int MAX_LIMB_PARTS = 19;

    private static final int STAND_INDEX = 20;
    private static final int HEAD_INDEX = 21;
    private static final int BODY_INDEX = 22;
    private static final int ARM_LEFT_INDEX = 23;
    private static final int LEG_LEFT_INDEX = 25;

    private static final int DIFFERENCE_BETWEEN_ATTEMPT_AND_INDEX = 1;

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
        if (mistakes <= MAX_HEAD_AND_BODY_PARTS) {
            hangmanState[mistakes - 1] = '=';
        } else if (mistakes <= MAX_TORSO_PARTS) {
            hangmanState[mistakes - 1] = '|';
        } else if (mistakes <= MAX_LIMB_PARTS) {
            hangmanState[mistakes - 1] = '-';
        } else {
            hangmanState[mistakes - DIFFERENCE_BETWEEN_ATTEMPT_AND_INDEX] = switch (mistakes) {
                case STAND_INDEX, BODY_INDEX -> '|';
                case HEAD_INDEX -> 'O';
                case ARM_LEFT_INDEX, LEG_LEFT_INDEX -> '/';
                default -> '\\';
            };
        }

        return updateContext(context, hangmanState);
    }


    private HangmanGameContext updateContext(HangmanGameContext context, char[] hangmanState) {
        return new HangmanGameContext(
                context.attempts(),
                context.expectedWord(),
                new Hangman(hangmanState),
                context.guessedLetters(),
                context.hint()
        );
    }
}
