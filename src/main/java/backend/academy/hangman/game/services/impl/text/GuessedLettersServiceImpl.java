package backend.academy.hangman.game.services.impl.text;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;
import backend.academy.hangman.game.services.GuessedLettersService;

public class GuessedLettersServiceImpl implements GuessedLettersService {

    @Override
    public Word updateWord(Word word, Input input) {
        var newWord = new char[word.text().length];
        var iterator = new WordIterator(word);
        int i = 0;

        while (iterator.hasNext()) {
            char letter = iterator.next();

            if (letter != '\0' || letter == input.input()) {
                newWord[i] = letter;

                if (letter == input.input()) {
                    break;
                }
            } else {
                newWord[i] = input.input();
                break;
            }

            i++;
        }

        return new Word(newWord);
    }
}
