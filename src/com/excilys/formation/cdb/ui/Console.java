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

    public static void main(String[] args) {
    	
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
    
    
    private static void displayAllComputers(){
    	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	ComputersList computers = computerDAO.findAll();
	    System.out.println(computers);
    }
    
    private static void displayAllCompanies(){
    	CompanyDAO companyDAO = new CompanyDAO(ConnectionDAO.getInstance());
    	CompaniesList companies = companyDAO.findAll();
	    System.out.println(companies);
    }
    
    private static void displayComputerInfo() {
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
    
    private static void insertComputer(){
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
	    if(sc.nextLine().equals("o")){
		    System.out.println("Veuillez entrer l'identifiant de l'entreprise.");
	    	c.setCompany(sc.nextLine());
	    }
	    computerDAO.createComputer(c);
	    System.out.println("Ordinateur inséré dans la base de données.");
	    sc.close();
    }

    private static void deleteComputer(){
    	Scanner sc = new Scanner(System.in);
    	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	System.out.println("Entrez l'identifiant de l'ordinateur :");
	    try{
	    	computerDAO.delete(sc.nextInt());
	    	sc.nextLine();
	    } catch(InputMismatchException ime){
	    	System.err.println("[Erreur : l'identifiant doit être un entier]");
	    }
	    finally {
	    	sc.close();
	    }
    }
    
    private static void updateComputer(){
    	 boolean updated = false;
		    System.out.println("id ?");
		    while (!updated) {

		    }
    }
    
    private static void closeApplication(){
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
    
    private static void displayMenu(){
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
