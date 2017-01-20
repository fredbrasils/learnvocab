package br.com.learnvocab.validation;

import br.com.learnvocab.entity.Word;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 */
public class WordValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
            return Word.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "word", "word.word.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priority", "word.priority.required");

        Word word = (Word) target;
        if (word.getIdiom() == null || word.getIdiom().getId() == null) {
            errors.rejectValue("idiom", "word.idiom.required");
        }
    }
}
