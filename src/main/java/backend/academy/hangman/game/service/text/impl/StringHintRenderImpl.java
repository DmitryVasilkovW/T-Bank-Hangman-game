package backend.academy.hangman.game.service.text.impl;

import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Renderable;
import backend.academy.hangman.game.service.text.StringRender;

public class StringHintRenderImpl implements StringRender {

    @Override
    public String render(Renderable renderable) {
        HangmanGameContext context = (HangmanGameContext) renderable;
        char[] hintChars = context.hint().text();
        return new String(hintChars);
    }

}
