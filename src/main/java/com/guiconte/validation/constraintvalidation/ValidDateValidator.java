package com.guiconte.validation.constraintvalidation;

import com.guiconte.validation.ValidDate;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, LocalDate> {

  @Override
  public void initialize(ValidDate constraintAnnotation) {}

  @Override
  public boolean isValid(LocalDate data_nascimento, ConstraintValidatorContext context) {
    if(data_nascimento != null)
      return !data_nascimento.isAfter(LocalDate.now());
    return true;
  }
}
