package com.springcourse.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.springcourse.demo.domain.WatchlistItem;

public class BadMovieValidator implements ConstraintValidator<BadMovie, WatchlistItem> {

	@Override
	public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
		return !(Double.valueOf(value.getRating()) <= 6 
				&& value.getComment().length() <= 15);
	}

}
