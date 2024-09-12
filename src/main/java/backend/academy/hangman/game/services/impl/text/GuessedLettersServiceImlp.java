package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.GuessedLettersService;

public class GuessedLettersServiceImlp implements GuessedLettersService {

    @Override
    public Word updateWord(Word word, Input input) {
        var newWord = new char[word.text().length];

        for (int i = 0; i < word.text().length; i++) {
            if (word.text()[i] != '\0') {
                newWord[i] = word.text()[i];
            } else {
                newWord[i] = input.input();
                break;
            }
        }

        return new Word(newWord);
    }
}
