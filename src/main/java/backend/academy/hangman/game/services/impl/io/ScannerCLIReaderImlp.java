package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.services.InputReader;
import java.util.Scanner;

public class ScannerCLIReaderImlp implements InputReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Input read() {
        return new Input(scanner.next().charAt(0));
    }
}
