package br.com.learnvocab.validation;

import br.com.learnvocab.entity.Box;
import br.com.learnvocab.entity.Word;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 */
public class BoxValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
            return Box.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "daysNumber", "box.daysnumber.required");        
        
    }
}
