package backend.academy.hangman.game.service.io.impl;

import backend.academy.hangman.game.service.io.StringPrinter;

public class CLIStringPrinterImpl implements StringPrinter {
    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void println(String content) {
        System.out.println(content);
    }
}
