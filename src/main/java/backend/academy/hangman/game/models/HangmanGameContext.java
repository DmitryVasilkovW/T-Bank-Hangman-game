package backend.academy.hangman.game.models;

public record HangmanGameContext(int attempts, Word expectedWord, Hangman hangman, Word guessedLetters, Word hint) implements Renderable {}
