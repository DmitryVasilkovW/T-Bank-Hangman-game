package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.services.StringReader;
import java.util.Scanner;

public class ScannerCLIReaderImlp implements StringReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
