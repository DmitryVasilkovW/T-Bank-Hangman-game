package backend.academy.hangman.game.service;

import java.util.Set;

public interface RandomSetter {

    String getRandomCategory(Set<String> categories);

    String getRandomDifficulty(Set<String> difficulties);

    int getRandomAttemptsBetweenSixAndTwentySix();
}
