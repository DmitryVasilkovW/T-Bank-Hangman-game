package backend.academy.hangman.game.services.impl.hangman;

public class HangmanStateService {
    private final char[] hangmanState;
    private int mistakes;

    public HangmanStateService(int mistakes) {
        this.mistakes = mistakes;
        hangmanState = new char[28];

        for (int i = 0; i < 28; i++) {
            hangmanState[i] = ' ';
        }
    }

    public void addPart() {
        switch (++mistakes) {
            case 1: hangmanState[2] = 'O'; break;     // Голова
            case 2: hangmanState[5] = '|'; break;     // Тело
            case 3: hangmanState[4] = '/'; break;     // Левая рука
            case 4: hangmanState[6] = '\\'; break;    // Правая рука
            case 5: hangmanState[7] = '/'; break;     // Левая нога
            case 6: hangmanState[8] = '\\'; break;    // Правая нога
        }
    }

    // Метод для рендеринга текущего состояния виселицы
    public String render() {
        return String.format(
            """
              %c%c%c%c%c
              %c   %c
             %c%c%c  %c
             %c%c%c  %c
             %c %c  %c
                  %c
            %c%c%c%c%c%c%c%c%c
            """,
            '+', '-', '-', '-', '+',
            '|',                                                    '|',
            hangmanState[1], hangmanState[2], hangmanState[3],      '|',
            hangmanState[4], hangmanState[5], hangmanState[6],      '|',
            hangmanState[7],                  hangmanState[8],      '|',
                                                                    '|',
            '=', '=', '=', '=', '=', '=', '=', '=', '='
        );
    }
}




