package backend.academy.hangman.game.myExceptions;

public class CategoryIsNotSetException extends Exception {
    public CategoryIsNotSetException(String message) {
        super(message);
    }

    public CategoryIsNotSetException() {
        super("Category is not set");
    }
}
