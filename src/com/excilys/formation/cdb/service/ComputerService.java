package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;
import com.excilys.formation.cdb.persistence.ConnectionDAO;

public class ComputerService {
    
    private ComputerDAO computerDAO;
    
    public ComputerService(){
	super();
	this.computerDAO = new ComputerDAO(ConnectionDAO.getInstance());
    }
    
    public List<Computer> findAll() {	
	return computerDAO.findAll();
    }
    
    public Computer find(int id) {
    	return computerDAO.find(id);
    }
    
    public void create(String name, String introduced, String discontinued, String idCompany) {
	computerDAO.create(name, introduced, discontinued, idCompany);
    }
    
    public void delete(int id) {
	computerDAO.delete(id);
    }
    
    public void update(Computer computer) {
	computerDAO.update(computer);
    }
}
