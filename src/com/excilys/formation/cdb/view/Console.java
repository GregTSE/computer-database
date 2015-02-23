package com.excilys.formation.cdb.view;

import java.util.Scanner;

import com.excilys.formation.cdb.model.CompaniesList;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.model.ComputersList;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.formation.excilys.validator.InputValidator;

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
     * @author Gregori Tirsatine
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
			    insertComputer();
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
			    System.err.println("[Erreur : veuillez entrer l'un des numéros du menu]");
		}
	    } else {
		System.err.println("[Erreur : veuiller entrer un nombre]");
	    }
	}
    }
   
    
    /**
     * Display the current list of the computers
     * @author Gregori Tirsatine
     */
    private void displayAllComputers() {
	//modifier les computers en computersDTO
    	ComputersList computers = computerService.findAll();
	System.out.println(computers);
    }
    
    /**
     * Display the complete list of the companies
     * @author Gregori Tirsatine
     */
    private void displayAllCompanies() {
    	CompaniesList companies = companyService.findAll();
    	System.out.println(companies);
    }
    
    /**
     * Display the informations about a computer by id
     * @author Gregori Tirsatine
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
		System.err.println("[Error : Id not found]");
	    }
	} else {
	    System.err.println("[Error : wrong Id format]");
	}
    }
    
    /**
     * Call the method to insert a new computer in the database
     * @author Gregori Tirsatine
     */
    private void insertComputer() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Nom de l'ordinateur ?");
	Computer c = new Computer(sc.nextLine());
	System.out.println("Voulez-vous entrer une date de production ? ('o'/'n')");
	if (sc.nextLine().equals("o")) {
	    System.out.println("Date ? (format aaaa-mm-jj)");
	    String date = sc.nextLine();
	    if (InputValidator.checkDateFormat(date)) {
		c.setDateIntroduced(date);
	    }
	    else {
		System.err.println("[Erreur : date non prise en compte]");
	    }
	}
	System.out.println("Voulez-vous entrer une date de fin de production ? ('o'/'n')");
	if (sc.nextLine().equals("o")) {
	    System.out.println("Date ? (format aaaa-mm-jj)");
	    String date = sc.nextLine();
	    if (InputValidator.checkDateFormat(date)) {
		c.setDateDiscontinued(date);
	    }
	    else {
		System.err.println("[Erreur : date non prise en compte]");
	    }
	}
	System.out.println("Voulez-vous préciser l'entreprise ? ('o'/'n')");
	if (sc.nextLine().equals("o")) {
	    System.out.println("Veuillez entrer l'identifiant de l'entreprise.");
	    c.setCompany(sc.nextLine());
	}
	computerService.insert(c);
	System.out.println("Ordinateur inséré dans la base de données.");
    }

    /**
     * Call the method to delete a computer from the database
     * @author Gregori Tirsatine
     */
    private void deleteComputer() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Entrez l'identifiant de l'ordinateur :");
    	
    	String id = sc.nextLine();
    	if (InputValidator.isInt(id)) {
    	    computerService.delete(Integer.parseInt(id));
    	} else {
    	    System.err.println("[Error : wrong Id format]");
    	}
    }
    
    /**
     * Call the method to update a computer
     */
    private void updateComputer(){
	Scanner sc = new Scanner(System.in);
    	System.out.println("Entrez l'identifiant de l'ordinateur à mettre à jour :");
    	
    	String id = sc.nextLine();
    	if( InputValidator.isInt(id)) {
    	    Computer computer = computerService.find(Integer.parseInt(id));
    	    if ( computer == null ) {
    		System.out.println("Cet ordinateur n'existe pas");
    	    } else {
    		System.out.println(computer.toString()+"\n");
    		System.out.println("Souhaitez-vous modifier le nom ? ('o'/'n')");
    		if (sc.nextLine().equals("o")) {
    		    System.out.println("Veuillez entrer le nom de l'ordinateur.");
    		    computer.setName(sc.nextLine());
    		}
    		
    		System.out.println("Souhaitez-vous modifier la date de production ? ('o'/'n')");
		if (sc.nextLine().equals("o")) {
		    System.out.println("Date ? (format aaaa-mm-jj)");
		    String date = sc.nextLine();
		    if (InputValidator.checkDateFormat(date)) {
			computer.setDateIntroduced(date);
		    }
		    else {
			System.err.println("[Erreur : date non prise en compte]");
		    }
		}
    		
		System.out.println("Souhaitez-vous modifier la date de fin de production ? ('o'/'n')");
    		if (sc.nextLine().equals("o")) {
		    System.out.println("Date ? (format aaaa-mm-jj)");
		    String date = sc.nextLine();
		    if (InputValidator.checkDateFormat(date)) {
			computer.setDateDiscontinued(date);
		    }
		    else {
			System.err.println("[Erreur : date non prise en compte]");
		    }
		}
    		
    		System.out.println("Souhaitez-vous mettre à jour l'entreprise ? ('o'/'n')");
    		if (sc.nextLine().equals("o")) {
    		    System.out.println("Veuillez entrer l'identifiant de l'entreprise.");
    		    
    		    computer.setCompany(sc.nextLine());
    		}
    		computerService.update(computer);
    		System.out.println("Ordinateur mis à jour.");
    	    }
	} else {
	    System.err.println("[Erreur : l'identifiant doit être un entier]");
	}	    
    }
    
    /**
     * Close the application
     * @author Gregori Tirsatine
     */
    private void closeConnection(){
	ConnectionDAO.close();
	System.out.println("Fin de l'application.");
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
	System.out.println("7 - Close the application");
    }
}
