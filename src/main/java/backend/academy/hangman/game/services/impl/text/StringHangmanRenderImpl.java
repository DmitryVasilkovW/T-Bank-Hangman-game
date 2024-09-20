package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Renderable;
import backend.academy.hangman.game.services.StringRender;

public class StringHangmanRenderImpl implements StringRender {

    private static final int HEAD_INDEX = 20;
    private static final int BODY_INDEX = 21;
    private static final int LEFT_ARM_INDEX = 22;
    private static final int RIGHT_ARM_INDEX = 23;
    private static final int LEFT_LEG_INDEX = 24;
    private static final int RIGHT_LEG_INDEX = 25;
    private static final int LEFT_HAND_INDEX = 26;
    private static final int RIGHT_HAND_INDEX = 27;

    private static final int GROUND_INDEX = 9;
    private static final int BASE_INDEX_1 = 10;
    private static final int BASE_INDEX_2 = 11;
    private static final int BASE_INDEX_3 = 12;
    private static final int BASE_INDEX_4 = 13;
    private static final int BASE_INDEX_5 = 14;
    private static final int BASE_INDEX_6 = 19;

    private static final int LOWER_ROW_0 = 0;
    private static final int LOWER_ROW_1 = 1;
    private static final int LOWER_ROW_2 = 2;
    private static final int LOWER_ROW_3 = 3;
    private static final int LOWER_ROW_4 = 4;
    private static final int LOWER_ROW_5 = 5;
    private static final int LOWER_ROW_6 = 6;
    private static final int LOWER_ROW_7 = 7;
    private static final int LOWER_ROW_8 = 8;

    private static final int UPPER_ROW_0 = 15;
    private static final int UPPER_ROW_1 = 16;
    private static final int UPPER_ROW_2 = 17;
    private static final int UPPER_ROW_3 = 18;

    @Override
    public String render(Renderable renderable) {
        char[] hangmanState = ((HangmanGameContext) renderable).hangman().hangman();

        return String.format(
            """
              %c%c%c%c%c
              %c   %c
             %c%c%c  %c
             %c%c%c  %c
             %c %c  %c
                  %c
            %c%c%c%c%c%c%c%c%c
            """,
            hangmanState[UPPER_ROW_3],
                hangmanState[UPPER_ROW_2],
                hangmanState[UPPER_ROW_1],
               hangmanState[UPPER_ROW_0],
                hangmanState[BASE_INDEX_5],
            hangmanState[BASE_INDEX_6],
                hangmanState[BASE_INDEX_4],
            hangmanState[LEFT_HAND_INDEX],
                hangmanState[HEAD_INDEX],
                hangmanState[RIGHT_HAND_INDEX],
                hangmanState[BASE_INDEX_3], hangmanState[LEFT_ARM_INDEX], hangmanState[BODY_INDEX],
                hangmanState[RIGHT_ARM_INDEX], hangmanState[BASE_INDEX_2],
            hangmanState[LEFT_LEG_INDEX],
                hangmanState[RIGHT_LEG_INDEX], hangmanState[BASE_INDEX_1],
                hangmanState[GROUND_INDEX],
            hangmanState[LOWER_ROW_0],
                hangmanState[LOWER_ROW_1],
                hangmanState[LOWER_ROW_2],
                hangmanState[LOWER_ROW_3],
                hangmanState[LOWER_ROW_4],
                hangmanState[LOWER_ROW_5],
                hangmanState[LOWER_ROW_6],
                hangmanState[LOWER_ROW_7],
                hangmanState[LOWER_ROW_8]
        );
    }
}
