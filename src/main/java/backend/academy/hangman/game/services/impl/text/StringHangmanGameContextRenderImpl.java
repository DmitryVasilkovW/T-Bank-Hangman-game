package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.HangmanGameContext;
import backend.academy.hangman.game.services.StringHangmanGameContextRender;
import backend.academy.hangman.game.services.StringRender;

public class StringHangmanGameContextRenderImpl implements StringHangmanGameContextRender {

    private final StringRender hungmanStringRender;
    private final StringRender guessedLettersStringRender;
    private final StringRender hintStringRender;
    private final StringRender attemptsStringRender;

    public StringHangmanGameContextRenderImpl(
            StringRender hungmanStringRender,
            StringRender guessedLettersStringRender,
            StringRender hintStringRender,
            StringRender attemptsStringRender) {

        this.hungmanStringRender = hungmanStringRender;
        this.guessedLettersStringRender = guessedLettersStringRender;
        this.hintStringRender = hintStringRender;
        this.attemptsStringRender = attemptsStringRender;
    }

    @Override
    public String renderHangman(HangmanGameContext context) {
        return hungmanStringRender.render(context);
    }

    @Override
    public String renderGuessedLetters(HangmanGameContext context) {
        return guessedLettersStringRender.render(context);
    }

    @Override
    public String renderAttempts(HangmanGameContext context) {
        return attemptsStringRender.render(context);
    }

    @Override
    public String renderHint(HangmanGameContext context) {
        return hintStringRender.render(context);
    }
}
