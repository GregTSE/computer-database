package com.excilys.formation.cdb.view;

import java.util.List;
import java.util.Scanner;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.formation.excilys.validator.InputValidator;

//@TODO voir pour les problemes de sc.close()

public class Console {
    
    private ComputerService computerService;
    private CompanyService companyService;

    public Console(){
	super();
	this.computerService = new ComputerService();
	this.companyService = new CompanyService();
    }
    
    /**
     * Method to start the application
     */
    public void start() {
	
    	boolean stopApp = false;
    	Scanner sc = new Scanner(System.in);
    	
	while (!stopApp) {	
	    displayMenu();
	    String choice = sc.nextLine();
	    
	    if (InputValidator.isInt(choice)) {
		
		switch (Integer.parseInt(choice)) {
			case 1:
			    displayAllComputers();
			    break;
			case 2:
			    displayAllCompanies();
			    break;
			case 3:
			    displayComputerInfo();
			    break;
			case 4:
			    createComputer();
			    break;
			case 5:
			    deleteComputer();
			    break;
			case 6:
			   updateComputer();
			   break;
			case 7:
			    closeConnection();
			    sc.close();
			    stopApp = true;
			    break;
			default:
			    System.out.println("[Please put a number (1-7)]");
		}
	    } else {
		System.out.println("[Please put a number (1-7)]");
	    }
	}
    }
   
    
    /**
     * Display the current list of the computers
     */
    private void displayAllComputers() {
    	List<Computer> computers = computerService.findAll();
    	for (Computer computer : computers) {
    	    System.out.println(computer.toBasicString());
    	}
    }
    
    /**
     * Display the complete list of the companies
     */
    private void displayAllCompanies() {
    	List<Company> companies = companyService.findAll();
    	for (Company company : companies) {
    	    System.out.println(company.toString());
    	}
    }
    
    /**
     * Display the informations about a computer by id
     */
    private void displayComputerInfo() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the computer's id : ");
	String id = sc.nextLine();
	if (InputValidator.isInt(id)) {
	    Computer computer = computerService.find(Integer.parseInt(id));
	    if(computer!=null) {
		System.out.println(computer);
	    } else { 
		System.out.println("[Error : ID not found]");
	    }
	} else {
	    System.out.println("[Error : wrong ID format]");
	}
	//sc.close();
    }
    
    /**
     * Call the method to insert a new computer in the database
     */
    private void createComputer() {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Name of the computer :");	
	String name = sc.nextLine();
	
	System.out.println("Introduced date : (format aaaa-mm-jj)");
	String introduced  = sc.nextLine();
	if (!InputValidator.checkDateFormat(introduced)) {
    		introduced = null;
	}	
	
	System.out.println("Discontinued date : (format aaaa-mm-jj)");
	String discontinued = sc.nextLine();
	if (!InputValidator.checkDateFormat(discontinued)) {
    		discontinued = null;
    	}
	
	System.out.println("ID of the Company :");    
	String idCompany = sc.nextLine();
	
	computerService.create(name, introduced, discontinued, idCompany);
	System.out.println("Success : ");
	//sc.close();
    }

    /**
     * Call the method to delete a computer from the database
     */
    private void deleteComputer() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("ID of computer :");
    	
    	String id = sc.nextLine();
    	if (InputValidator.isInt(id)) {
    	    computerService.delete(Integer.parseInt(id));
    	} else {
    	    System.err.println("[Error : wrong Id format]");
    	}
    	//sc.close();
    }
    
    /**
     * Call the method to update a computer
     */
    private void updateComputer(){
	Scanner sc = new Scanner(System.in);
    	System.out.println("ID of computer :");
    	
    	String id = sc.nextLine();
    	if( InputValidator.isInt(id)) {
    	    Computer computer = computerService.find(Integer.parseInt(id));
    	    if ( computer == null ) {
    		System.out.println("This computer does not exist.");
    	    } else {
    		System.out.println(computer.toString()+"\n");
    		System.out.println("Name of the computer:");
    		computer.setName(sc.nextLine());
    	    }
    	    
    	    System.out.println("Introduced date : (format aaaa-mm-jj)");
    	    String introduced  = sc.nextLine();
    	    if (InputValidator.checkDateFormat(introduced)) {
    		computer.setDateIntroduced(introduced);
    	    }

    		
    	    System.out.println("Discontinued date : (format aaaa-mm-jj)");
    	    String discontinued = sc.nextLine();
    	    if (InputValidator.checkDateFormat(discontinued)) {
    		computer.setDateDiscontinued(discontinued);
    	    }
    	
    	    System.out.println("ID of the Company :");   
   
    	    computerService.update(computer);
    	    System.out.println("Update OK.");
 
    	} else {
    	    System.out.println("[ID must be an integer]");
    	}
    	sc.close();
    }
    
    /**
     * Close the application
     * @author Gregori Tirsatine
     */
    private void closeConnection(){
	ConnectionDAO.close();
	System.out.println("Application closed.");
    }
    
    /**
     * Display the main menu
     * @author Gregori Tirsatine
     */
    private void displayMenu(){
	System.out.println("\n\nMENU");
	System.out.println("1 - Display all computers");
	System.out.println("2 - Display all companies");
	System.out.println("3 - Display the informations of a computer");
	System.out.println("4 - Insert a computer");
	System.out.println("5 - Delete a computer");
	System.out.println("6 - Update a computer");
	System.out.println("7 - Quit");
    }
}
