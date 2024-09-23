package backend.academy.hangman.game.service.impl.io;

import backend.academy.hangman.game.model.Input;
import backend.academy.hangman.game.model.Word;
import backend.academy.hangman.game.service.CharacterInputValidator;
import backend.academy.hangman.game.service.HangmanGameInputValidator;
import backend.academy.hangman.game.service.StringInputValidator;

public class HangmanGameInputValidatorImpl implements HangmanGameInputValidator {

    private final CharacterInputValidator acceptedCharactersValidator;
    private final StringInputValidator stringInputLengthValidator;
    private final StringInputValidator stringHintInputValidator;

    public HangmanGameInputValidatorImpl(
            CharacterInputValidator acceptedCharactersValidator,
            StringInputValidator stringInputLengthValidator,
            StringInputValidator stringHintInputValidator) {
        this.acceptedCharactersValidator = acceptedCharactersValidator;
        this.stringInputLengthValidator = stringInputLengthValidator;
        this.stringHintInputValidator = stringHintInputValidator;
    }

    @Override
    public boolean hasInputAccepted(Input text, Word word) {
        return acceptedCharactersValidator.hasInputAccepted(text, word);
    }

    @Override
    public boolean isValidLengthOfInput(String input) {
        return stringInputLengthValidator.isValid(input);
    }

    @Override
    public boolean isInputValueOfHintButton(String input) {
        return stringHintInputValidator.isValid(input);
    }
}
