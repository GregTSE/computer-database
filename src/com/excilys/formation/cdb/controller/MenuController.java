package com.excilys.formation.cdb.controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.formation.cdb.dao.ComputerDAO;
import com.excilys.formation.cdb.dao.ConnectionDAO;

public class MenuController {
    
    public MenuController(){
	super();
    }
    
    private void deleteComputer() {
    	Scanner sc0 = new Scanner(System.in);
    	ComputerDAO computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    	System.out.println("Entrez l'identifiant de l'ordinateur :");
	    try{
	    	computerDAO.delete(sc0.nextInt());
	    } catch(InputMismatchException ime){
	    	System.err.println("[Erreur : l'identifiant doit être un entier]");
	    }
    }
}
