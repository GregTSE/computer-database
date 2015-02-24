package com.formation.excilys.validator;

public class InputValidator {
    
    public static boolean isInt(String input) {
	return input.matches("[0-9]+");
    }
    
    public static boolean checkDateFormat(String date) {
	
	if (date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
	    return true;
	} else {
	    System.out.println("[Wrong format]");
	    return false;
	}
    }
    
    
    
}
