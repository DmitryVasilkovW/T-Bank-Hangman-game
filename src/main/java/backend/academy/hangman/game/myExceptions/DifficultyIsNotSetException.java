package backend.academy.hangman.game.myExceptions;

public class DifficultyIsNotSetException extends Exception {
    public DifficultyIsNotSetException(String message) {
        super(message);
    }

    public DifficultyIsNotSetException() {
        super("Difficulty is not set");
    }
}
