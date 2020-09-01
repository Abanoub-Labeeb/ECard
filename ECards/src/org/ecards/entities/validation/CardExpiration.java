package org.ecards.entities.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
@Constraint(validatedBy = CardExpirationValidator.class)
public  @interface CardExpiration {
	
	String message() default "Expiration invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
