package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class ComputerService {

    private ComputerDAO computerDAO;

    public ComputerService() {
	super();
	this.computerDAO = new ComputerDAO();
    }

    public List<Computer> findAll() {
	return computerDAO.findAll();
    }

    public List<Computer> findAll(int num, int offset) {
	return computerDAO.findAll(num, offset);
    }

    public Computer find(int id) {
	return computerDAO.find(id);
    }

    public void create(String name, String introduced, String discontinued, Company company) {
	computerDAO.create(name, introduced, discontinued, company);
    }

    public void delete(int id) {
	computerDAO.delete(id);
    }

    public void update(Computer computer) {
	computerDAO.update(computer);
    }

    public List<Computer> search(String str, int num, int offset) {
	return computerDAO.search(str, num, offset);
    }

    public int count() {
	return computerDAO.count();
    }
}
