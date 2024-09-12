package backend.academy.hangman.game.myExceptions;

public class AttemptsAreNotSetException extends Exception {
    public AttemptsAreNotSetException(String message) {
        super(message);
    }

    public AttemptsAreNotSetException() {
        super("Attempts are not set");
    }
}
