package com.excilys.formation.cdb.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.formation.cdb.utils.Util;

public class InputValidatorTest {

    @Test
    public void testIsInt() {
	assertFalse(Util.checkDigit("3.12"));
	assertTrue(Util.checkDigit("0"));
	assertFalse(Util.checkDigit("test"));
	assertFalse(Util.checkDigit(null));
    }

    @Test
    public void testCheckDateFormat() {
	assertTrue(Util.checkEnglishDateFormat("2000-01-01"));
	//month 13
	assertFalse(Util.checkEnglishDateFormat("2000-13-01"));
	//day 32
	assertFalse(Util.checkEnglishDateFormat("2000-10-32"));
	//years : 3 numbers
	assertFalse(Util.checkEnglishDateFormat("199-01-01"));
	//date null
	assertFalse(Util.checkEnglishDateFormat(null));
	//empty String
	assertFalse(Util.checkEnglishDateFormat(""));
	assertFalse(Util.checkEnglishDateFormat("test"));
    }

}
