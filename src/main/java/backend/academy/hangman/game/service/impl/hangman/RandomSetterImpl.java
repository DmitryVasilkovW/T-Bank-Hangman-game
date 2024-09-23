package backend.academy.hangman.game.service.impl.hangman;

import backend.academy.hangman.game.service.RandomSetter;
import java.util.Random;
import java.util.Set;

public class RandomSetterImpl implements RandomSetter {
    private final Random random = new Random();
    private static final int MIN_ATTEMPTS = 6;
    private static final int ATTEMPT_RANGE = 21;

    @Override
    public String getRandomCategory(Set<String> categories) {
        int index = random.nextInt(categories.size());

        return categories.toArray(new String[0])[index];
    }

    @Override
    public String getRandomDifficulty(Set<String> difficulties) {
        int index = random.nextInt(difficulties.size());

        return difficulties.toArray(new String[0])[index];
    }

    @Override
    public int getRandomAttemptsBetweenSixAndTwentySix() {
        return random.nextInt(ATTEMPT_RANGE) + MIN_ATTEMPTS;
    }
}
