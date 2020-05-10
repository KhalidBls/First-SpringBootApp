package com.springcourse.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.trim().length() > 0 
				&& value.trim().length() <=4 
				&& Double.valueOf(value.trim()) <= 10.00
				&& Double.valueOf(value.trim()) >= 0;
	}

	

}
