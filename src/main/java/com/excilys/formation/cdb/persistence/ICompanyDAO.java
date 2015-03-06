package com.excilys.formation.cdb.persistence;

import java.util.List;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyDAO {

    /**
     * Find all companies
     * @return companies list
     */

    public abstract void delete(Long id);

    public abstract List<Company> findAll();

}