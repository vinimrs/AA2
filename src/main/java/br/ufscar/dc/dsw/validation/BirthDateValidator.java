package br.ufscar.dc.dsw.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
public class BirthDateValidator implements ConstraintValidator<BirthDate, LocalDate> {

  @Override
  public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
    if (birthDate == null) return false;

    LocalDate today = LocalDate.now();
    return birthDate.isBefore(today);
  }
}