package com.excilys.formation.cdb.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;

public class DateValidator implements Validator{

    public DateValidator() {
	// TODO Auto-generated constructor stub
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> arg0) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(T arg0, Class<?>... arg1) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T arg0,
	    String arg1, Class<?>... arg2) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> arg0,
	    String arg1, Object arg2, Class<?>... arg3) {
	// TODO Auto-generated method stub
	return null;
    }

}
