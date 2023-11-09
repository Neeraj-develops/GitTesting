package com.spring.Helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator_imp implements ConstraintValidator<ImageNameValidator, String> {
    private Logger logger = LoggerFactory.getLogger(ImageNameValidator_imp.class);
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        logger.info("message from logger",s);
        //logic
        if (s.isBlank()){
            return false;
        }else{
            return true;
        }

    }
}
