package backend.academy.hangman.game.exception;

public class DifficultyIsNotSetException extends RuntimeException {

    public DifficultyIsNotSetException() {
        super("Difficulty is not set");
    }
}
