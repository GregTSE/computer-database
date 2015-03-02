package com.excilys.formation.cdb.utils;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.lang3.StringUtils;

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

//    public static Company getCompany(String id) {
//	Company company = null;
//	if (checkInt(id)) {
//	    CompanyService cs = new CompanyService();
//	    cs.find(Long.parseLong(id));
//	}
//	return company;
//    }

}
