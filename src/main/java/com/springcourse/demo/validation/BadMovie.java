package com.springcourse.demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BadMovieValidator.class)
public @interface BadMovie {
	
		String message() default "If a movie is as bad as 6 or lower, the comment size should be composed at least by 15 characters";
		
		Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};

	
}
