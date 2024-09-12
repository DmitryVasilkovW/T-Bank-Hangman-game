package backend.academy.hangman.game.myExceptions;

public class IncorrectAttemptsException extends Exception {
    public IncorrectAttemptsException(String message) {
        super(message);
    }

    public IncorrectAttemptsException(int attempts) {
        super("Incorrect attempts: " + attempts + " you can choose from 1 to 26");
    }
}
