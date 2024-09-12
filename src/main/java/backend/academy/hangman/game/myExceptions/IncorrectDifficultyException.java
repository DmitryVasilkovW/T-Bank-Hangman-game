package backend.academy.hangman.game.myExceptions;

public class IncorrectDifficultyException extends Exception {
    public IncorrectDifficultyException(String message) {
        super(message);
    }

    public IncorrectDifficultyException() {
        super("Incorrect difficulty");
    }
}
