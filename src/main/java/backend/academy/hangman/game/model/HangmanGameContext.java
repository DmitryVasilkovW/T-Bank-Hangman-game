package backend.academy.hangman.game.model;

public record HangmanGameContext(
        int attempts,
        Word expectedWord,
        Hangman hangman,
        Word guessedLetters,
        Word hint
) implements Renderable {}