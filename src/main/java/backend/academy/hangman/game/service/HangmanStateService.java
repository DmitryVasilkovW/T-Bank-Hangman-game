package backend.academy.hangman.game.service;

import backend.academy.hangman.game.model.HangmanGameContext;

public interface HangmanStateService {
    HangmanGameContext addPart(HangmanGameContext context);
}
