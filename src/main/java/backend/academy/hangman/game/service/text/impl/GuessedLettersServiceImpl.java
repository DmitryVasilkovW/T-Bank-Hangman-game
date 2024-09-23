package backend.academy.hangman.game.service.text.impl;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.text.GuessedLettersService;

public class GuessedLettersServiceImpl implements GuessedLettersService {

    @Override
    public Word updateWord(Word word, Input input) {
        var newWord = new char[word.text().length];
        var iterator = new WordIterator(word);
        int i = 0;
        boolean isContained = false;

        while (iterator.hasNext()) {
            char letter = iterator.next();

            if (letter == '\0' && isContained) {
                break;
            }

            if (letter != '\0' || letter == input.input()) {
                newWord[i] = letter;

                if (letter == input.input()) {
                    isContained = true;
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
