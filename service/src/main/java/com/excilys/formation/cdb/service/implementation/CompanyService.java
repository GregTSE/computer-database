package com.excilys.formation.cdb.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ICompanyDAO;
import com.excilys.formation.cdb.persistence.IComputerDAO;
import com.excilys.formation.cdb.service.ICompanyService;

@Service
@Transactional
//TODO Bug : en enlevant transactional
public class CompanyService implements ICompanyService {
    
   @Autowired
    private ICompanyDAO companyDAO;
   @Autowired
   private IComputerDAO computerDAO;
    
    public CompanyService() {
	
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
    @Transactional
    public void delete(Long id) {
	computerDAO.deleteByCompany(id);
	companyDAO.delete(id);
    }
    
    @Override
    public Company find(Long id) {
	return companyDAO.find(id);
    }
    


}
