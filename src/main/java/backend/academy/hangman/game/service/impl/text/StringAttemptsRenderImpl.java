package backend.academy.hangman.game.service.impl.text;

import backend.academy.hangman.game.model.HangmanGameContext;
import backend.academy.hangman.game.model.Renderable;
import backend.academy.hangman.game.service.StringRender;

public class StringAttemptsRenderImpl implements StringRender {

    @Override
    public String render(Renderable renderable) {
        var sb = new StringBuilder();

        sb
            .append("your attempts: ")
            .append(((HangmanGameContext) renderable).attempts());

        return sb.toString();
    }
}
