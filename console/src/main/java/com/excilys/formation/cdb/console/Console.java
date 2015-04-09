package com.excilys.formation.cdb.console;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.cli.WebServiceCompany;
import com.excilys.formation.cdb.cli.WebServiceComputer;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.utils.Util;

/**
 * Console user interface
 * 
 * @author Greg T.
 */

@Component
public class Console {

    final private Logger logger = LoggerFactory.getLogger(Console.class);
    @Autowired
    private WebServiceCompany webServiceCompany;
    @Autowired
    private WebServiceComputer webServiceComputer;

    public Console() {
	super();
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
	    if (Util.checkDigit(choice)) {
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
		    deleteCompany(sc);
		    break;
		case 8:
		    sc.close();
		    stopApp = true;
		    break;
		default:
		    logger.error("user's choice is not between 1 and 8");
		}
	    } else {
		logger.error("user's choice is not an integer");
	    }
	}
    }

    /**
     * Display the list of the computers
     */
    private void displayAllComputers() {
	List<ComputerDTO> computers = webServiceComputer.getAll();
	for (ComputerDTO computerDTO : computers) {
	    System.out.println(computerDTO.toString());
	}
    }

    /**
     * Display the list of the companies
     */
    private void displayAllCompanies() {
	List<Company> companies = webServiceCompany.getAll();
	for (Company company : companies) {
	    System.out.println(company.toString());
	}
    }

    /**
     * Display the informations about a computer by id
     * 
     * @param Scanner
     */
    private void displayComputerInfo(Scanner sc) {
	Logger logger = LoggerFactory.getLogger(Console.class);
	System.out.println("Enter the computer's id : ");
	String id = sc.nextLine();
	if (Util.checkDigit(id)) {
	    ComputerDTO computerDTO = webServiceComputer.get(id);
	    if (computerDTO.getId() != 0) {
		System.out.println(computerDTO);
	    } else {
		logger.error("ID not found");
	    }
	} else {
	    logger.error("wrong ID format");
	}
    }

    /**
     * Call the method to insert a new computer in the database
     * 
     * @param Scanner
     */
    private void createComputer(Scanner sc) {
	System.out.println("Name of the computer :");
	String name = sc.nextLine();
	System.out.println("Introduced date : (format yyyy-mm-dd)");
	String introduced = sc.nextLine();
	System.out.println("Discontinued date : (format yyyy-mm-dd)");
	String discontinued = sc.nextLine();
	System.out.println("ID of the Company :");
	String idCompany = sc.nextLine();
	Company company = null;
	if (Util.checkDigit(idCompany)) {
	    company = webServiceCompany.get(idCompany);
	}
	ComputerDTO computerDTO = new ComputerDTO(name, introduced,
		discontinued, "");
	if (company != null) {
	    computerDTO.setCompanyId(Long.parseLong(idCompany));
	    computerDTO.setCompanyName(company.getName());
	}
	webServiceComputer.add(computerDTO);
    }

    /**
     * Call the method to delete a computer from the database
     * 
     * @param Scanner
     */
    private void deleteComputer(Scanner sc) {
	System.out.println("ID of computer :");
	String id = sc.nextLine();
	if (Util.checkDigit(id)) {
	    String returnMsg = webServiceComputer.delete(Integer.parseInt(id));
	    System.out.println(returnMsg);
	} else {
	    logger.error("wrong Id format");
	}
    }

    /**
     * Call the method to update a computer
     * 
     * @param Scanner
     */
    private void updateComputer(Scanner sc) {
	System.out.println("ID of computer :");
	String id = sc.nextLine();
	if (Util.checkDigit(id)) {
	    ComputerDTO computerDTO = webServiceComputer.get(id);
	    if (computerDTO == null) {
		logger.error("This computer does not exist.");
	    } else {
		System.out.println(computerDTO.toString() + "\n");
		System.out.println("Name of the computer:");
		computerDTO.setName(sc.nextLine());
	    }

	    System.out.println("Introduced date : (format yyyy-mm-dd)");
	    String introduced = sc.nextLine();
	    computerDTO.setDateIntroduced(introduced);

	    System.out.println("Discontinued date : (format yyyy-mm-dd)");
	    String discontinued = sc.nextLine();
	    computerDTO.setDateIntroduced(discontinued);

	    webServiceComputer.update(computerDTO);
	    System.out.println("Update OK.");

	} else {
	    logger.error("ID must be an integer");
	}
    }

    /**
     * Delete company and all its computers
     * 
     * @param sc
     */
    public void deleteCompany(Scanner sc) {
	System.out.println("Enter the company ID : ");
	String id = sc.nextLine();
	if (Util.checkDigit(id)) {
	    webServiceCompany.delete(Long.parseLong(id));
	}
    }

    /**
     * Display the main menu
     */
    private void displayMenu() {
	System.out.println("\n\nMENU");
	System.out.println("1 - Display all computers");
	System.out.println("2 - Display all companies");
	System.out.println("3 - Display the informations of a computer");
	System.out.println("4 - Insert a computer");
	System.out.println("5 - Delete a computer");
	System.out.println("6 - Update a computer");
	System.out.println("7 - Delete a company");
	System.out.println("8 - Quit");
    }

}
