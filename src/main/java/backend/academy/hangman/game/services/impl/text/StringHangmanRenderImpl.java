package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Renderable;
import backend.academy.hangman.game.services.StringRender;

public class StringHangmanRenderImpl implements StringRender {

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
            hangmanState[18], hangmanState[17], hangmanState[16], hangmanState[15], hangmanState[14],
            hangmanState[19],                                                       hangmanState[13],
            hangmanState[26], hangmanState[20], hangmanState[27],                   hangmanState[12],
            hangmanState[22], hangmanState[21], hangmanState[23],                   hangmanState[11],
            hangmanState[24],                   hangmanState[25],                   hangmanState[10],
            hangmanState[9],
            hangmanState[0],  hangmanState[1],  hangmanState[2],  hangmanState[3],  hangmanState[4], hangmanState[5], hangmanState[6], hangmanState[7], hangmanState[8]
        );
    }
}
