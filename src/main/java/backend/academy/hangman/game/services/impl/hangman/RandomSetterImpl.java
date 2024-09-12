package backend.academy.hangman.game.services.impl.hangman;

import backend.academy.hangman.game.services.RandomSetter;
import java.util.Random;
import java.util.Set;

public class RandomSetterImpl implements RandomSetter {
    private final Random random = new Random();

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
    public int getRandomAttempts() {
        return random.nextInt(21) + 6;
    }
}
