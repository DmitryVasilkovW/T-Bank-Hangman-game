package backend.academy.hangman.game.service.impl.io;

import backend.academy.hangman.game.service.StringReader;
import java.util.Scanner;

public class ScannerCLIReaderImlp implements StringReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
