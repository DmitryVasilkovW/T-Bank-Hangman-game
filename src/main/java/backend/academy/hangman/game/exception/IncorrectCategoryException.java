package backend.academy.hangman.game.exception;

public class IncorrectCategoryException extends RuntimeException {

    public IncorrectCategoryException() {
        super("Incorrect category");
    }
}
