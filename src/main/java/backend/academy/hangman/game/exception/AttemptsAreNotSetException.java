package backend.academy.hangman.game.exception;

public class AttemptsAreNotSetException extends RuntimeException {

    public AttemptsAreNotSetException() {
        super("Attempts are not set");
    }
}
