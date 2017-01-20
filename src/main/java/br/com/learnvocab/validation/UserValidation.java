package br.com.learnvocab.validation;

import br.com.learnvocab.entity.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 */
public class UserValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "user.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");

        User user = (User) target;
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "user.roles.required");
        }
        
        if (user.getEmail() != null && !isValidEmailAddress(user.getEmail())){ 
            errors.rejectValue("email","user.email.invalid");    
        }
    }
    
    /* Valid email */
    public boolean isValidEmailAddress(String emailAddress){ 
        String expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"; 
        CharSequence inputStr = emailAddress; 
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE); 
        Matcher matcher = pattern.matcher(inputStr); 
        return matcher.matches(); 
    }  
}
