package backend.academy.hangman.game.myExceptions;

public class IncorrectAttemptsException extends Exception {
    public IncorrectAttemptsException(String message) {
        super(message);
    }
}
