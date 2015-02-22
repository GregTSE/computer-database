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

	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
	CompanyDAO companyDAO = new CompanyDAO(ConnectionDAO.getInstance());
	boolean stop = false;

	while (!stop) {

	    Scanner sc = new Scanner(System.in);
	    System.out.println("\n\nMENU");
	    System.out.println("1 - Afficher la liste des ordinateurs");
	    System.out.println("2 - Afficher la liste des entreprises");
	    System.out.println("3 - Afficher les détails d'un ordinateur");
	    System.out.println("4 - Insérer un ordinateur dans la base");
	    System.out.println("5 - Supprimer un ordinateur dans la base");
	    System.out.println("6 - Mettre à jour un ordinateur");
	    try {
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
		    ComputersList computers = computerDAO.findAll();
		    System.out.println(computers);
		    break;
		case 2:
		    CompaniesList companies = companyDAO.findAll();
		    System.out.println(companies);
		    break;
		case 3:
		    System.out
			    .println("Entrez l'identifiant de l'ordinateur : ");
		    Computer computer = computerDAO.find(sc.nextInt());
		    if(computer!=null)
		    	System.out.println(computer);
		    else 
		    	System.out.println("Identifiant non trouvé.");
		    break;
		case 4:
		    System.out.println("NOM ?");
		    Computer c = new Computer(sc.next());
		    System.out.println("DATE ? (format aaaa-mm-jj");
		    String date = sc.next();
		    if(date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")){
		    	c.setDateIntroduced(Date.valueOf(date));
		    	computerDAO.createComputer(c);
		    	System.out.println("Odinateur inséré dans la BDD");
		    }
		    else
		    	System.out.println("Mauvaise date : insertion avortée.");
		    break;
		case 5:
		    System.out.println("id ?");
		    computerDAO.delete(sc.nextInt());
		    break;
		case 6:
		    boolean updated = false;
		    System.out.println("id ?");
		    while (!updated) {

		    }

		    break;
		case 7:
		    sc.close();
		    Connection conn = ConnectionDAO.getInstance();
		    if (conn != null) {
			try {
			    conn.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			    System.err.println("Erreur de fermeture BDD.");
			}
		    }
		    stop = true;
		    break;
		default:
		    System.out.println("Mauvaise entrée, veuillez entrez l'un des numéros du menu");

		}
	    } catch (InputMismatchException ime) {
		System.out
			.println("Mauvaise entrée, veuillez entrez l'un des numéros du menu");
	    }
	}
	System.out.println("Fin de l'application.");
    }

}
