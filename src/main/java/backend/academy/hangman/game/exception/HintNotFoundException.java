package backend.academy.hangman.game.exception;

public class HintNotFoundException extends RuntimeException {

    public HintNotFoundException(String message) {
        super(message);
    }
}
