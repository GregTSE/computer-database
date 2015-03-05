package com.excilys.formation.cdb.service.implementation;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.implementation.CompanyDAO;
import com.excilys.formation.cdb.service.ICompanyService;

public class CompanyService implements ICompanyService {

    public CompanyService() {
	super();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.ICompanyService#findAll()
     */
    @Override
    public List<Company> findAll() {
	return CompanyDAO.INSTANCE.findAll();
    }
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.ICompanyService#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
	CompanyDAO.INSTANCE.delete(id);
    }

}
