package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;

public class CompanyService {

    private CompanyDAO companyDAO;

    public CompanyService() {
	super();
	this.companyDAO = new CompanyDAO();
    }

    public List<Company> findAll() {
	return companyDAO.findAll();
    }

    public Company find(Long id) {
	return companyDAO.find(id);
    }
}
