package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.services.GameSetupReader;
import java.util.Scanner;

public class ScannerCLIGameSetupReaderImpl implements GameSetupReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readSettingForWordAsString() {
        return scanner.nextLine();
    }
}
