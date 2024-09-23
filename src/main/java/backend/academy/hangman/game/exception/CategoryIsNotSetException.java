package backend.academy.hangman.game.exception;

public class CategoryIsNotSetException extends RuntimeException {

    public CategoryIsNotSetException() {
        super("Category is not set");
    }
}
