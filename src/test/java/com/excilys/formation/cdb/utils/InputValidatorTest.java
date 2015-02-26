package com.excilys.formation.cdb.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.formation.cdb.utils.Util;

public class InputValidatorTest {

    @Test
    public void testIsInt() {
	assertFalse(Util.checkId("3.12"));
	assertTrue(Util.checkId("0"));
	assertFalse(Util.checkId("test"));
	assertFalse(Util.checkId(null));
    }

    @Test
    public void testCheckDateFormat() {
	assertTrue(Util.checkDateFormat("2000-01-01"));
	//month 13
	assertFalse(Util.checkDateFormat("2000-13-01"));
	//day 32
	assertFalse(Util.checkDateFormat("2000-10-32"));
	//years : 3 numbers
	assertFalse(Util.checkDateFormat("199-01-01"));
	//date null
	assertFalse(Util.checkDateFormat(null));
	//empty String
	assertFalse(Util.checkDateFormat(""));
	assertFalse(Util.checkDateFormat("test"));
    }

}
