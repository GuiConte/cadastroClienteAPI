package com.guiconte.validation;

import com.guiconte.validation.constraintvalidation.ValidDateValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidDateValidator.class)
public @interface ValidDate {

  String message() default "Data de nascimento invalida!";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

}
