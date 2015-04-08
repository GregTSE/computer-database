package com.excilys.formation.cdb.utils;


import java.util.Locale;


import org.apache.commons.validator.routines.DateValidator;
import org.springframework.context.i18n.LocaleContextHolder;

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
     * Check if the parameter is a digit
     * @param id Computer's id
     * @return true if the format is correct.
     */
    public static boolean checkDigit(String id) {
	if (id != null) {
	    return id.matches("[0-9]+");
	}
	return false;
    }

    /**
     * Check the format date
     * @param date
     * @return true if the format is correct
     */
    public static boolean checkDateFormat(String date) {
	DateValidator dateValidator = DateValidator.getInstance();
	return dateValidator.isValid(date, "dd-MM-yyyy") || dateValidator.isValid(date, "yyyy-MM-dd");
    }

}
