package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Renderable;
import backend.academy.hangman.game.services.StringRender;

public class StringHintRenderImpl implements StringRender {

    @Override
    public String render(Renderable renderable) {
        HangmanGameContext context = (HangmanGameContext) renderable;
        char[] hintChars = context.hint().text();
        return new String(hintChars);
    }

}
