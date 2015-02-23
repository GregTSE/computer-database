package com.excilys.formation.cdb.ui;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.formation.cdb.bean.CompaniesList;
import com.excilys.formation.cdb.bean.Computer;
import com.excilys.formation.cdb.bean.ComputersList;
import com.excilys.formation.cdb.dao.CompanyDAO;
import com.excilys.formation.cdb.dao.ComputerDAO;
import com.excilys.formation.cdb.dao.ConnectionDAO;

public class Console {

    public Console(){
	super();
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
		   	
		    try {
		    	int choice = sc.nextInt();
		    	sc.nextLine();
				switch (choice) {
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
						closeApplication();
						sc.close();
						stopApp = true;
					    break;
					default:
					    System.err.println("[Erreur : veuillez entrer l'un des numéros du menu]");
				}
		    } catch (InputMismatchException ime) {
		    	System.err.println("[Erreur : veuillez entrer un nombre]");
		    	sc.nextLine();
		    }
		}
    }
    
    /**
     * Display the current list of the computers
     * @author Gregori Tirsatine
     */
    private void displayAllComputers(){
    	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	ComputersList computers = computerDAO.findAll();
	    System.out.println(computers);
    }
    
    /**
     * Display the complete list of the companies
     * @author Gregori Tirsatine
     */
    private void displayAllCompanies(){
    	CompanyDAO companyDAO = new CompanyDAO(ConnectionDAO.getInstance());
    	CompaniesList companies = companyDAO.findAll();
	    System.out.println(companies);
    }
    
    /**
     * Display the informations about a computer by id
     * @author Gregori Tirsatine
     */
    private void displayComputerInfo() {
    	Scanner sc = new Scanner(System.in);
    	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	System.out.println("Entrez l'identifiant de l'ordinateur : ");
	    try {
	    	Computer computer = computerDAO.find(sc.nextInt());
	    	if(computer!=null)
		    	System.out.println(computer);
		    else 
		    	System.err.println("[Identifiant non trouvé]");
	    } catch (InputMismatchException ime){
	    	System.err.println("[Erreur d'identifiant]");
	    	sc.nextLine();
	    }
    }
    
    /**
     * Call the method to insert a new computer in the database
     * @author Gregori Tirsatine
     */
    private void insertComputer(){
    	Scanner sc = new Scanner(System.in);
    	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	System.out.println("Nom de l'ordinateur ?");
	    Computer c = new Computer(sc.nextLine());
	    System.out.println("Voulez-vous entrer une date de production ? ('o'/'n')");
	    if(sc.nextLine().equals("o")){
		System.out.println("Date ? (format aaaa-mm-jj)");
		String date = sc.nextLine();
		if(date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")){
		    c.setDateIntroduced(Date.valueOf(date));
		}
		else {
		    System.err.println("[Erreur : date non prise en compte]");
		}
	    }
	    System.out.println("Voulez-vous entrer une date de fin de production ? ('o'/'n')");
	    if(sc.nextLine().equals("o")){
		System.out.println("Date ? (format aaaa-mm-jj)");
		String date = sc.nextLine();
		if(date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")){
		    c.setDateDiscontinued(Date.valueOf(date));
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
	    computerDAO.createComputer(c);
	    System.out.println("Ordinateur inséré dans la base de données.");
    }

    /**
     * Call the method to delete a computer from the database
     * @author Gregori Tirsatine
     */
    private void deleteComputer(){
    	Scanner sc0 = new Scanner(System.in);
    	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	System.out.println("Entrez l'identifiant de l'ordinateur :");
	    try{
	    	computerDAO.delete(sc0.nextInt());
	    } catch(InputMismatchException ime){
	    	System.err.println("[Erreur : l'identifiant doit être un entier]");
	    }
    }
    
    /**
     * Call the method to update a computer
     */
    private void updateComputer(){
	Scanner sc = new Scanner(System.in);
	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	System.out.println("Entrez l'identifiant de l'ordinateur à mettre à jour :");
    	try {
    	    Computer computer = computerDAO.find(sc.nextInt());
    	    sc.nextLine();
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
		if(sc.nextLine().equals("o")){
		    System.out.println("Date ? (format aaaa-mm-jj)");
		    String date = sc.nextLine();
		    if (date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
			computer.setDateIntroduced(Date.valueOf(date));
		    }
		    else {
			System.err.println("[Erreur : date non prise en compte]");
		    }
		}
    		
		System.out.println("Souhaitez-vous modifier la date de fin de production ? ('o'/'n')");
    		if(sc.nextLine().equals("o")){
		    System.out.println("Date ? (format aaaa-mm-jj)");
		    String date = sc.nextLine();
		    if (date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
			computer.setDateDiscontinued(Date.valueOf(date));
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
    		computerDAO.update(computer);
    		System.out.println("Ordinateur mis à jour.");
    	    }
	    
	} catch(InputMismatchException ime) {
	    System.err.println("[Erreur : l'identifiant doit être un entier]");
	}
    	
		    
		    
    }
    
    /**
     * Close the application
     * @author Gregori Tirsatine
     */
    private void closeApplication(){
	    Connection conn = ConnectionDAO.getInstance();
	    if (conn != null) {
			try {
			    conn.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			    System.err.println("[Erreur de fermeture BDD]");
			}
	    }
	    System.out.println("Fin de l'application.");
    }
    
    /**
     * Display the main menu
     * @author Gregori Tirsatine
     */
    private void displayMenu(){
	    System.out.println("\n\nMENU");
	    System.out.println("1 - Afficher la liste des ordinateurs");
	    System.out.println("2 - Afficher la liste des entreprises");
	    System.out.println("3 - Afficher les détails d'un ordinateur");
	    System.out.println("4 - Insérer un ordinateur dans la base");
	    System.out.println("5 - Supprimer un ordinateur dans la base");
	    System.out.println("6 - Mettre à jour un ordinateur");
	    System.out.println("7 - Quitter");
    }
}
