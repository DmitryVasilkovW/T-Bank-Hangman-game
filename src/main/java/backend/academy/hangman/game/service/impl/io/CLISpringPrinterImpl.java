package backend.academy.hangman.game.service.impl.io;

import backend.academy.hangman.game.service.StringPrinter;

public class CLISpringPrinterImpl implements StringPrinter {
    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void println(String content) {
        System.out.println(content);
    }
}
