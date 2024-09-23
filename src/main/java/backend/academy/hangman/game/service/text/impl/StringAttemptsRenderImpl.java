package backend.academy.hangman.game.service.text.impl;

import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Renderable;
import backend.academy.hangman.game.service.text.StringRender;

public class StringAttemptsRenderImpl implements StringRender {

    @Override
    public String render(Renderable renderable) {
        return "your attempts: "
                + ((HangmanGameContext) renderable).attempts();
    }
}
