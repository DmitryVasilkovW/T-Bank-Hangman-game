package backend.academy.hangman.game.myExceptions;

public class IncorrectCategoryException extends Exception {
    public IncorrectCategoryException(String message) {
        super(message);
    }

    public IncorrectCategoryException() {
        super("Incorrect category");
    }
}
