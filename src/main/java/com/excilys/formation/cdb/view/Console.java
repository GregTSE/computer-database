package com.excilys.formation.cdb.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.utils.Util;

/**
 * Console user interface
 * @author Greg T.
 */
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
	    
	    if (Util.checkId(choice)) {
		
		switch (Integer.parseInt(choice)) {
			case 1:
			    displayAllComputers();
			    break;
			case 2:
			    displayAllCompanies();
			    break;
			case 3:
			    displayComputerInfo(sc);
			    break;
			case 4:
			    createComputer(sc);
			    break;
			case 5:
			    deleteComputer(sc);
			    break;
			case 6:
			   updateComputer(sc);
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
     * @param Scanner
     */
    private void displayComputerInfo(Scanner sc) {
    	System.out.println("Enter the computer's id : ");
	String id = sc.nextLine();
	if (Util.checkId(id)) {
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
     * @param Scanner
     */
    private void createComputer(Scanner sc) {
    	
    	System.out.println("Name of the computer :");	
	String name = sc.nextLine();
	
	System.out.println("Introduced date : (format aaaa-mm-jj)");
	String introduced  = sc.nextLine();
	if (!Util.checkDateFormat(introduced)) {
    		introduced = null;
	}	
	
	System.out.println("Discontinued date : (format aaaa-mm-jj)");
	String discontinued = sc.nextLine();
	if (!Util.checkDateFormat(discontinued)) {
    		discontinued = null;
    	}
	
	System.out.println("ID of the Company :");    
	String idCompany = sc.nextLine();
	Company company = null;
	if (Util.checkId(idCompany)) {
	    company = Util.getCompany(idCompany);
	}
	computerService.create(name, introduced, discontinued, company);
	System.out.println("Success : ");
    }

    /**
     * Call the method to delete a computer from the database
     * @param Scanner
     */
    private void deleteComputer(Scanner sc) {
    	System.out.println("ID of computer :");
    	
    	String id = sc.nextLine();
    	if (Util.checkId(id)) {
    	    computerService.delete(Integer.parseInt(id));
    	} else {
    	    System.err.println("[Error : wrong Id format]");
    	}
    	//sc.close();
    }
    
    /**
     * Call the method to update a computer
     * @param Scanner
     */
    private void updateComputer(Scanner sc){
    	System.out.println("ID of computer :");
    	
    	String id = sc.nextLine();
    	if( Util.checkId(id)) {
    	    Computer computer = computerService.find(Integer.parseInt(id));
    	    if ( computer == null ) {
    		System.out.println("This computer does not exist.");
    	    } else {
    		System.out.println(computer.toString()+"\n");
    		System.out.println("Name of the computer:");
    		computer.setName(sc.nextLine());
    	    }
    	    
  
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	    
    	    System.out.println("Introduced date : (format aaaa-mm-jj)");
    	    String introduced  = sc.nextLine();
    	    if (Util.checkDateFormat(introduced)) {
    		 computer.setDateIntroduced(LocalDate.parse(introduced, formatter));
    	    }

    		
    	    System.out.println("Discontinued date : (format aaaa-mm-jj)");
    	    String discontinued = sc.nextLine();
    	    if (Util.checkDateFormat(discontinued)) {
    		computer.setDateIntroduced(LocalDate.parse(discontinued, formatter));
    	    }
    	
    	    System.out.println("ID of the Company :");   
   
    	    computerService.update(computer);
    	    System.out.println("Update OK.");
 
    	} else {
    	    System.out.println("[ID must be an integer]");
    	}
    }
    
    /**
     * Close the application
     */
    private void closeConnection(){
	ConnectionDAO.close();
	System.out.println("Application closed.");
    }
    
    /**
     * Display the main menu
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
