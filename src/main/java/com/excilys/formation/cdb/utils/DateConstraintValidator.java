package com.excilys.formation.cdb.utils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements
	ConstraintValidator<DateAnnotation, String> {

    public DateConstraintValidator() {
	super();
    }

    @Override
    public void initialize(DateAnnotation date) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext arg1) {
	if (date == null || date.equals("")) {
	    return true;
	}
	return Util.checkDateFormat(date);

    }

}