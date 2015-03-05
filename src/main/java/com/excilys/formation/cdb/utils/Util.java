package com.excilys.formation.cdb.utils;

import org.apache.commons.validator.routines.DateValidator;
/**
 * Provide static methods to check properties
 * 
 * @author GregTSE
 *
 */
final public class Util {

   
    private Util(){
	super();
    }
    
    /**
     * 
     * @param id
     *            Computer's id
     * @return true if the format is correct.
     */
    public static boolean checkInt(String id) {
	if (id != null) {
	    return id.matches("[0-9]+");
	}
	return false;
    }

    public static boolean checkDateFormat(String date) {
	DateValidator dateValidator = DateValidator.getInstance();
	return dateValidator.isValid(date, "yyyy-MM-dd");
    }

}
