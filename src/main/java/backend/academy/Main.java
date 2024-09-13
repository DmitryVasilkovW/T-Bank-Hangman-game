package backend.academy;

import backend.academy.hangman.game.services.impl.hangman.CLIHangmanGameFactoryImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        var game = new CLIHangmanGameFactoryImpl().createHangmanGame(20, "eagle");
        game.move();
    }
}


