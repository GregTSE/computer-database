package com.excilys.formation.cdb.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.formation.cdb.utils.InputValidator;

public class InputValidatorTest {

    @Test
    public void testIsInt() {
	assertFalse(InputValidator.isInt("3.12"));
	assertTrue(InputValidator.isInt("0"));
	assertFalse(InputValidator.isInt("test"));
	assertFalse(InputValidator.isInt(null));
    }

    @Test
    public void testCheckDateFormat() {
	assertTrue(InputValidator.checkDateFormat("2000-01-01"));
	//month 13
	assertFalse(InputValidator.checkDateFormat("2000-13-01"));
	//day 32
	assertFalse(InputValidator.checkDateFormat("2000-10-32"));
	//years : 3 numbers
	assertFalse(InputValidator.checkDateFormat("199-01-01"));
	//date null
	assertFalse(InputValidator.checkDateFormat(null));
	//empty String
	assertFalse(InputValidator.checkDateFormat(""));
	assertFalse(InputValidator.checkDateFormat("test"));
    }

}
