package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.models.Renderable;
import backend.academy.hangman.game.services.StringRender;

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
