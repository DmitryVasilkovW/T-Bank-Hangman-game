package backend.academy.hangman.game.service.text;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;

public interface GuessedLettersService {
    Word updateWord(Word word, Input input);
}
