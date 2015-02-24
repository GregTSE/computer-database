package com.excilys.formation.cdb.controller;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.persistence.ConnectionDAO;

public class Controller {
    
    private ComputerDAO computerDAO;
    private CompanyDAO companyDAO;
    
    public Controller(){
	super();
	this.computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
	this.companyDAO = new CompanyDAO(ConnectionDAO.getInstance());
    }
    
//    public ComputersList findAllComputers() {	
//	return computerDAO.findAll();
//    }
    
//    public CompaniesList findAllCompanies() {
//    	return companyDAO.findAll();
//    }
    
    public Computer findById(int id) {
    	return computerDAO.find(id);
    }
    
//    public void createComputer(Computer computer) {
//	computerDAO.createComputer(computer);
//    }
    
    public void deleteComputer(int id) {
	computerDAO.delete(id);
    }
    
    public void updateComputer(Computer computer) {
	computerDAO.update(computer);
    }
    
    public boolean checkDate(String date) {
	return date.matches("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])");
    }
    
 
}
