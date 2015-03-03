package com.excilys.formation.cdb.view;

import org.apache.commons.validator.routines.DateValidator;

/**
 * Launcher to execute the application with a console interface
 * 
 * @author Gregori Tirsatine
 *
 */
public class Launcher {

    public static void main(String[] args) {
	Console console = new Console();
	//console.start();
	
	DateValidator dateValidator = DateValidator.getInstance();
	System.out.println(dateValidator.isValid("2000r-12-31", "yyyy-MM-dd"));
    }

}
