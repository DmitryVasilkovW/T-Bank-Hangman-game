package backend.academy;

import backend.academy.hangman.game.services.impl.hangman.HangmanStateService;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        HangmanStateService service = new HangmanStateService(1);

        // Симуляция ошибок от 1 до 26
        for (int mistakes = 1; mistakes <= 6; mistakes++) {
            service.addPart(mistakes);
            System.out.println(service.render());
            System.out.println();
        }
    }
}


