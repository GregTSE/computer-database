package com.excilys.formation.cdb.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.ICompanyService;

public abstract class AbsCompanyService implements ICompanyService {

    
    public AbsCompanyService() {
    }

    @Override
    public final List<Company> findAll() {
	List<Company> companies = null;
	companies = findAllAbs();
	return companies;
    }

    @Override
    public final void delete(Long id) {
		    deleteAbs(id);
	
	}

    public abstract List<Company> findAllAbs();

    public abstract void deleteAbs(Long id);
    

}
