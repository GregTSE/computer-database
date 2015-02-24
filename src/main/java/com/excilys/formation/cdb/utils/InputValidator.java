package com.excilys.formation.cdb.utils;

public class InputValidator {
    
    public static boolean isInt(String input) {
	if (input != null) {
	    return input.matches("[0-9]+");
	}
	return false;
    }
    
    public static boolean checkDateFormat(String date) {
	if (date != null) {
        	if (date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
        	    return true;
        	}
	} 
	System.out.println("[Wrong format]");
	return false;
    }
    
}
