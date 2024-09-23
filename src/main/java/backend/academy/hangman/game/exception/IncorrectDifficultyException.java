package backend.academy.hangman.game.exception;

public class IncorrectDifficultyException extends RuntimeException {

    public IncorrectDifficultyException() {
        super("Incorrect difficulty");
    }
}
