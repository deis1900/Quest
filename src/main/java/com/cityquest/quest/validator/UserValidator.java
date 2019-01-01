package com.cityquest.quest.validator;

import com.cityquest.quest.model.User;
import com.cityquest.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

//    private final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
//            LOGGER.info("Username length is wrong.");
            errors.rejectValue("username", "Username from userForm have incorrect size.");
        }
        if (userService.findByUsername(user.getUsername()) == null) {
//            LOGGER.info("Not find user with username " + user.getUsername());
            errors.rejectValue("username", "User with "+ user.getUsername() + " not exists.");
        }

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//        if (user.getPassword().length() < 7 || user.getPassword().length() > 32) {
//            LOGGER.info("Password length is wrong");
//            errors.rejectValue("password", "Size.userForm.password");
//        }
    }
}