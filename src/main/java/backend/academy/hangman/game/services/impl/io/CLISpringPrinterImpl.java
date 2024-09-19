package backend.academy.hangman.game.services.impl.io;

import backend.academy.hangman.game.services.StringPrinter;

public class CLISpringPrinterImpl implements StringPrinter {

    @Override
    public void println(String content) {
        System.out.println(content);
    }
}
