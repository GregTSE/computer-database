package com.excilys.formation.cdb.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.validator.routines.DateValidator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
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
     * Check the english format date
     * @param date
     * @return true if the format is correct
     */
    public static boolean checkDateFormat(String date) {
	DateValidator dateValidator = DateValidator.getInstance();
	Locale locale = LocaleContextHolder.getLocale();
	String pattern = (locale.getLanguage().equals("fr") ? "dd-MM-yyyy" : "yyyy-MM-dd");
	System.out.println(date+ " :"+dateValidator.isValid(date, pattern));
	return dateValidator.isValid(date, pattern);
    }

}
