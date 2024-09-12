package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.Input;
import backend.academy.hangman.game.models.Word;

public interface GuessedLettersService {
    Word updateWord(Word word, Input input);
}
