package backend.academy.hangman.game.exception;

public class IncorrectAttemptsException extends RuntimeException {
    public IncorrectAttemptsException(String message) {
        super(message);
    }
}
