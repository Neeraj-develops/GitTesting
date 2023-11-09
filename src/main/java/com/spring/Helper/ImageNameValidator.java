package com.spring.Helper;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator_imp.class)
public @interface ImageNameValidator {
    String message() default "Invalid Image Name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
