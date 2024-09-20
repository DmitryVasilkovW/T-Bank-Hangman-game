package backend.academy.hangman.game.services;

import backend.academy.hangman.game.models.HangmanGameContext;

public interface HangmanStateService {
    HangmanGameContext addPart(HangmanGameContext context);
}
