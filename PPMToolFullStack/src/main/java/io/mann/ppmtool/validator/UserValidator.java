package io.mann.ppmtool.validator;

import io.mann.ppmtool.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @Author: Mann
 * @Date: 2019-06-03 22:36
 */
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if (user.getPassword().length() < 6) {
            errors.rejectValue("password","Length","Password must be at least 6 characters");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword","Length","Password must match");
        }
    }
}
