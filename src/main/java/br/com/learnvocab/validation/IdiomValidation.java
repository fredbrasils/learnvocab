package br.com.learnvocab.validation;

import br.com.learnvocab.entity.Idiom;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 */
public class IdiomValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
            return Idiom.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "idiom.name.required");            

//        Produto produto = (Produto) target;
//        if (produto.getPaginas() <= 0) {
//            errors.rejectValue("paginas", "field.required");
//        }
    }
}
