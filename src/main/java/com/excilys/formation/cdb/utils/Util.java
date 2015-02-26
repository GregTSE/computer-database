package com.excilys.formation.cdb.utils;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.CompanyService;

/**
 * Provide static methods to check properties
 * @author GregTSE
 *
 */
public class Util {
    
    /**
     * 
     * @param id Computer's id 
     * @return true if the format is correct.
     */
    public static boolean checkId(String id) {
	if (id != null) {
	    return id.matches("[0-9]+");
	}
	return false;
    }
    
    public static boolean checkDateFormat(String date) {
	if (date != null) {
	    if (date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
		return true;
	    }
	} 
	return false;
    }
    
    public static Company getCompany(String id) {
	Company company = null;
	if( checkId(id)) { 
	    CompanyService cs = new CompanyService();
	    cs.find(Long.parseLong(id));
	}
	return company;
    }
    
}
