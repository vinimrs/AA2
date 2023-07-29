package br.ufscar.dc.dsw.validation;

import br.ufscar.dc.dsw.validation.uniques.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
  String message() default "Input a valid name.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
