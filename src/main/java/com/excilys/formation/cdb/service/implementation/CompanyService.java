package com.excilys.formation.cdb.service.implementation;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.service.ICompanyService;

public class CompanyService implements ICompanyService {

    private CompanyDAO companyDAO;

    public CompanyService() {
	super();
	this.companyDAO = new CompanyDAO();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.ICompanyService#findAll()
     */
    @Override
    public List<Company> findAll() {
	return companyDAO.findAll();
    }
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.ICompanyService#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
	companyDAO.delete(id);
    }

}
